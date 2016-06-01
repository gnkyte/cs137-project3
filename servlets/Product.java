package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.DatabaseConnection;
import utilities.Shared;
import utilities.ShoeHistoryContainer;
import utilities.ShoppingCart;

/**
 * Servlet implementation class Product
 */
@WebServlet(name = "Product", urlPatterns = {"/Product"})
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Product() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if(session.getAttribute("history")==null) {
			session.setAttribute("history", new ShoeHistoryContainer());
		}
		

		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>"
				+ "<head>"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"custom.css\">"
				+ "</head>"
				+ "<title>Secret Life of Shoes | CS137 && INF124"
				+ "</title>");
//				+ "<body>");
		
		//connect to server
		
		String productID = request.getParameter("id");
//		productID="9";
		//add viewing the product to the history
		ShoeHistoryContainer history = (ShoeHistoryContainer) session.getAttribute("history");
		history.insert(Integer.parseInt(productID));
		if(productID == null) {
			out.println("404 Page not found<br>");
			out.print("Now go away :c");
			return;
		} else {
			//TODO add to search history
			try {
				DatabaseConnection db = new DatabaseConnection();
				if(db.connection == null) {
					out.println("ERROR: No connection");
					return;
				}
		        String query = "SELECT * FROM product where productID="+productID;
		        System.out.println(query);
		        Statement getShoe = db.connection.createStatement();
				ResultSet shoe = getShoe.executeQuery(query);
				
				if(shoe.next()) {
					outputShoe(shoe, out, db);
				} else {
					out.println("ERROR: Shoe not found!");
					return;
				}
				
				out.println("</body>"
						+ "</html>");
				
				db.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}			
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void outputShoe(ResultSet shoe, PrintWriter out, DatabaseConnection db) {
		try {
			//(productID, name, description, materials, price, gender, imagePath)
			int id = shoe.getInt(1);
			String name = shoe.getString(2);
			String description = shoe.getString(3);
			String materials = shoe.getString(4);
			double price = shoe.getDouble(5);
			String gender = shoe.getString(6);
			String imagePath = shoe.getString(7);
			
			if(gender.equals("M"))
				out.println("<body id=\"mens-shoes\" background=\"img/slide3.jpg\">");
			else
				out.println("<body id=\"womens-shoes\" background=\"img/slide4.jpg\">");

			out.println(Shared.getHeader( gender.equals("M") ? "men" : "women" ));

			
			ResultSet stock = getProductStockOptions(id, db);
			boolean inStock = true;
			if(!stock.isBeforeFirst()) {
				inStock=false;
			}
			out.println("<h2>"+name+"</h2>");
			out.println("<table border='1'>"
					+ "<tr>");
			out.println("<td>"
					+ "<img class=\"product-img\" src=\""+imagePath+"\" alt=\""+name+"\" >"
					+ "</td>");
			out.println("<td>");
			out.println("<p>$"+price+"</p>" //TODO format into 2 decimal places
					+"<p>Description: "+description+"</p>"
					+"<br><br><p>Materials: "+materials+"</p>");
			
			//form for ordering
			if(inStock) {
				out.println("<form method='post' action='AddToCart'>"
						+ "<input type='hidden' name='id' value='"+Integer.toString(id)+"'>"
						+ "<input type='hidden' name='name' value='"+name+"'>"
						+ "<input type='hidden' name='price' value='"+price+"'>"
						+ "size:" + printProductStockOptions(stock)
						+ "<br>quantity:<input type='number' name='quantity' required>"
						+ "<br><input type='submit' value='Add to Cart'"
						+ "</form>");
			} else {
				out.println("<p>Out of stock</p>");
			}
			
			out.println("</td>"
					+ "</tr>"
					+ "</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ResultSet getProductStockOptions(int id, DatabaseConnection db) {
		try {
			String query = "select size, quantity from stock where productID = "+Integer.toString(id);
			Statement getStock = db.connection.createStatement();
			ResultSet stock = getStock.executeQuery(query);
			return stock;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String printProductStockOptions(ResultSet stock) {
		//TODO deal with issue when the shoe size is there, but the quantity is zero
		//TODO make trigger to delete size if the quantity is zero
		//TODO in stock table, we don't need to know the name, just the ID
		try {
			String stockOutput = "<select name='size'>";
			double size;
			int quantity;
			int sizesInStock = 0;
			while(stock.next()) {
				size = stock.getDouble("size");
				quantity = stock.getInt("quantity");
				if(quantity > 0) {
					sizesInStock++;
					stockOutput += "<option value='"+size+"'>"+size+"</option>";
				}
			}
			stockOutput += "</select>";
			if(sizesInStock == 0) {
				stockOutput = "No shoes in stock";
			}
			System.out.println(stockOutput);
			return stockOutput;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

}
