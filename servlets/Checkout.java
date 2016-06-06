package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import utilities.SQLQueries;
import utilities.DatabaseConnection;
import utilities.ShoppingCart;
import utilities.Purchase;

@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet{
	
	public Checkout(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String bStreet = request.getParameter("street");
		String bCity = request.getParameter("city");
		String bState = request.getParameter("state");
		String bCountry = request.getParameter("country");
		String bZip = request.getParameter("zip");
		
		String sStreet = request.getParameter("shipstreet");
		String sCity = request.getParameter("shipcity");
		String sState = request.getParameter("shipstate");
		String sCountry = request.getParameter("shipcountry");
		String sZip = request.getParameter("shipzip");
		
		String email = request.getParameter("email");
		String credit = request.getParameter("creditcard");
		
		StringBuilder billAddr = new StringBuilder();
		StringBuilder shipAddr = new StringBuilder();
		billAddr.append(bStreet);
		billAddr.append(" ");
		billAddr.append(bCity);
		billAddr.append(", ");
		billAddr.append(bState);
		billAddr.append(" ");
		billAddr.append(bCountry);
		billAddr.append(" ");
		billAddr.append(bZip);
		
		shipAddr.append(sStreet);
		shipAddr.append(" ");
		shipAddr.append(sCity);
		shipAddr.append(", ");
		shipAddr.append(sState);
		shipAddr.append(" ");
		shipAddr.append(sCountry);
		shipAddr.append(" ");
		shipAddr.append(sZip);
		
		String billing = billAddr.toString();
		String shipping = shipAddr.toString();

		if(session.getAttribute("cart")==null) {
			session.setAttribute("cart", new ShoppingCart());
		}
		
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		
		if (cart.isEmpty()){
			out.println("Shopping Cart is empty.");
			return;
		}
		else{
			DatabaseConnection db = new DatabaseConnection();
			if(db.connection == null){
				out.println("Error: No connection to the database");
				return;
			}
			String[] params = new String[6];
			params[0] = name;
			params[1] = credit;
			params[2] = shipping;
			params[3] = billing;
			params[4] = email;
			if(session.getAttribute("purchase")==null) {
				session.setAttribute("purchase", new Purchase());
			}
			
			Purchase order = (Purchase)session.getAttribute("purchase");
			order.makePurchase(params);
			session.setAttribute("purchase", order);
			int status = SQLQueries.insertOrder(params, db.connection);
			if(status == 0){
				out.println("Error: Inserting Order has failed.");
				//response.sendRedirect("orderdetails.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("orderdetails.jsp");
				rd.forward(request, response);
			}
			else{
				//TODO: redirect to the checkout confirmation page
			}
			try {
				db.connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}	


