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

import utilities.DatabaseConnection;
import utilities.Shared;

/**
 * Servlet implementation class HomepageDisplay
 */
@WebServlet(name = "HomepageDisplay", urlPatterns = {"/HomepageDisplay"})
public class HomepageDisplay extends HttpServlet {
	final static int SHOES_PER_ROW = 3;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepageDisplay() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DatabaseConnection db = new DatabaseConnection();

		out.println("<html>"
				+ "<head>"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"custom.css\">"
				+ "</head>"
				+ "<title>Secret Life of Shoes | CS137 && INF124"
				+ "</title>"
				+ "<body>");
				
		out.println(Shared.getHeader("index"));
		
		if(db.connection == null) {
			out.println("no connection");
			out.println("Ughhh :C");
//			return;
		}
		
		out.println("     <div id=\"slider\">");
		out.println("             <a href=\"all_shoe.html\"><img src=\"img/slide0.jpg\" alt=\"jumbotron.jpg\" /></a>");
		out.println("     </div>");
		out.println("         <hr align=\"center\" size=\"1rem\" width=\"30%\"/>"); 
		 
		 
		
		ResultSet maleShoes = getShoes("M", db);
		outputShoes(maleShoes, db, out, "Men's Shoes");
		
		ResultSet femaleShoes = getShoes("F", db);
		outputShoes(femaleShoes, db, out, "Women's Shoes");
		
		ResultSet allShoes = getShoes("all", db);
		outputShoes(allShoes, db, out, "All Shoes");
		
		out.print("</body>"
				+ "</html>");
	
		try {
			if( db.connection != null )
				db.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
	
	private ResultSet getShoes(String gender, DatabaseConnection db) {
		//gets the shoes from the database based on the gender (or all gender)
		try {
	        String query = "SELECT productID, price, imagePath FROM product";
	        if(!gender.equals("all")) {
	        	query = query.concat(" where gender='"+gender+"'");
	        }
	        Statement getShoes = db.connection.createStatement();
			ResultSet shoes = getShoes.executeQuery(query);
			return shoes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void outputShoes(ResultSet shoes, DatabaseConnection connection, PrintWriter out, String header) {
		//outputs the shoes as a table that allows us to format the shoes in a nice way
		int shoeCol = 0;
//		out.println("<h3>"+header+"</h3>");
//		out.println("<table border='1'>"
//				+ "<tr>");
		
        out.println("<div id=\"product-index\">");
        out.println("<div id=\"womens-shoes-index\">");
        out.println("    <h1>" + header + "</h1>");
        out.println("    <hr class=\"separator\">");
        out.println("    <table id=\"womens-shoes-table\">");
        out.println("        <tr colspan=\"3\">");

		
		try {
			while(shoes.next()) {
				//(productID, name, description, materials, price, gender, imagePath) 
				int id = shoes.getInt(1);
				String name= shoes.getString("productID");
//				String literalName = shoes.getString("name");
				double price= shoes.getDouble("price");
				String imagePath= shoes.getString("imagePath");
				out.println("<td>");
				out.println("<a href='Product?id="+id+"'"
						+ "<img class=\"product-img\" src=\""+imagePath+"\" alt=\""+name+"\">"
						+ "<p>Path: " + imagePath + "</p><br />"
						+ "<p>Name: "+name+"</p><br />"
						+ "<p>$"+price+"</p><br />"
						+ "</a>");
				out.print("</td>");
				shoeCol++;
				if(shoeCol == SHOES_PER_ROW) {
					shoeCol=0;
					out.println("</tr><tr>");
				}
			}
			out.println("</tr></table></div></div>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
