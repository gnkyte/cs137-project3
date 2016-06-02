package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.DatabaseConnection;
import utilities.Shared;
import utilities.ShoeHistory;
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
		//connect to server
		
		String productID = request.getParameter("id");
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
		        Statement getShoe = db.connection.createStatement();
				ResultSet shoe = getShoe.executeQuery(query);
				
				if(shoe.next()) {
					outputShoe(session,shoe, out, db, request, response);
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
	
	private void outputShoe(HttpSession session, ResultSet shoe, PrintWriter out, DatabaseConnection db, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//(productID, name, description, materials, price, gender, imagePath)
			int id = shoe.getInt(1);
			String name = shoe.getString(2);
			String description = shoe.getString(3);
			String materials = shoe.getString(4);
			double price = shoe.getDouble(5);
			String gender = shoe.getString(6);
			String imagePath = shoe.getString(7);
			
			//add viewing the product to the history
			ShoeHistoryContainer history = (ShoeHistoryContainer) session.getAttribute("history");
			ShoeHistory sh = new ShoeHistory(id, name, imagePath);
			history.insert(sh);
			session.setAttribute("history", history);
			
			ResultSet stock = getProductStockOptions(id, db);
			boolean inStock = true;
			if(!stock.isBeforeFirst()) {
				inStock=false;
			}
			out.println("<head>");
			out.println("<title>"+name + "</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"custom.css\">");
//			navigationBar(out);
			
			if(gender.equals("M"))
				out.println("<body id=\"mens-shoes\" background=\"img/slide3.jpg\">");
			else
				out.println("<body id=\"womens-shoes\" background=\"img/slide4.jpg\">");

			out.println(Shared.getHeader( gender.equals("M") ? "men" : "women" ));

			Shared.getHeader(gender);
			
			out.println("<div id=\"shoe-description-container\">");
			out.println("    <br />");
			out.println("    <h2 id=\"shoe-title\">"+ name +"</h2>");
			out.println("    <table class=\"shoe\">");
			out.println("        <tr>");
			out.println("            <td>");
			out.println("                <img src=\"" + imagePath + "\" alt=\"" + imagePath + "\" width=\"300\" style=\"margin: 10px\">");
			out.println("            </td>");
			out.println("            <td>");
			out.println("                <p class=\"shoePrice\">$" + price + "</p>");
			out.println("                <p class=\"shoeDescription\">"+ description +"</p>");
			out.println("                <br><br>");
			out.println("                <p class=\"materials-list\">Materials: "+materials+"</p>");
			
			//form for ordering
			out.println("<br /><br />");
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
			
			out.println("            </td>");
			
            RequestDispatcher rd = request.getRequestDispatcher("Counter");
            rd.include(request, response);
            
			out.println("        </tr>");
			out.println("    </table>");
			out.println("</div>");

//			out.println("</head>");
//			out.println("<h2>"+name+"</h2>");
//			out.println("<table border='1'>"
//					+ "<tr>");
//			out.println("<td>"
//					+ "<img src=\""+imagePath+"\" alt=\""+name+"\" width=\"300\" style=\"margin: 10px\">"
//					+ "</td>");
//			out.println("<td>");
//			out.println("<p>$"+price+"</p>" //TODO format into 2 decimal places
//					+"<p>Description: "+description+"</p>"
//					+"<br><br><p>Materials: "+materials+"</p>");
			
//			out.println("</td>"
//					+ "</tr>"
//					+ "</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void navigationBar(PrintWriter out) {
		out.println("<table border=1>"
				+ "<tr>"
				+ "<td><a href='/Shoes'>Home</a></td>"
				+ "<td><a href='/Shoes/all_shoes.jsp'>All Shoes</a></td>"
				+ "<td><a href='/Shoes/male_shoes.jsp'>Men's Shoes</a></td>"
				+ "<td><a href='/Shoes/female_shoes.jsp'>Women's Shoes</a></td>"
				+ "<td><a href='/Shoes/ShoppingCart.jsp'>View Cart</a></td>"
				+ "<td><a href='/Shoes/about_us.html'>About Us</a></td>"
				+ "");

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
			return stockOutput;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

}
