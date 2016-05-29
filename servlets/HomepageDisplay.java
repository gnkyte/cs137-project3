package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.DatabaseConnection;

/**
 * Servlet implementation class HomepageDisplay
 */
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
		DatabaseConnection db= new DatabaseConnection();
		if(db.connection == null) {
			out.println("no connection");
			return;
		}
		out.println("<html>"
				+ "<title>"
				+ "</title>"
				+ "<body>");
		ResultSet maleShoes = getShoes("M", db);
		outputShoes(maleShoes, db, out, "Male Shoes");
		
		ResultSet femaleShoes = getShoes("F", db);
		outputShoes(femaleShoes, db, out, "Female Shoes");
		
		ResultSet allShoes = getShoes("all", db);
		outputShoes(allShoes, db, out, "All Shoes");
		
		out.print("</body>"
				+ "</html>");
		
	
		try {
			db.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		//TODO: make it look good.
		int shoeCol = 0;
		out.println("<h3>"+header+"</h3>");
		out.println("<table border='1'>"
				+ "<tr>");
		
		try {
			while(shoes.next()) {
				//(productID, name, description, materials, price, gender, imagePath) 
				int id = shoes.getInt(1);
				String name= shoes.getString("productID");
				double price= shoes.getDouble("price");
				String imagePath= shoes.getString("imagePath");
				out.println("<td>");
				out.println("<a href='Product?id="+id+"'"
						+ "<img src=\""+imagePath+"\" alt=\""+name+"\" width=\"300\" style=\"margin: 10px\">"
						+ "<p>"+name+"</p>"
						+ "<p>$"+price+"</p>"
						+ "</a>");
				out.print("</td>");
				shoeCol++;
				if(shoeCol == SHOES_PER_ROW) {
					shoeCol=0;
					out.println("</tr><tr>");
				}
			}
			out.println("</tr></table>");	
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
