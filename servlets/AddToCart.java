package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.ShoeHistoryContainer;
import utilities.ShoppingCart;

/**
 * Servlet implementation class AddToCart
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //check session stuff
		HttpSession session = request.getSession(true);

		if(session.getAttribute("cart")==null) {
			session.setAttribute("cart", new ShoppingCart());
		}
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		double size = Double.parseDouble(request.getParameter("size"));
		String quantityString = request.getParameter("quantity");
		int quantity = 0;
			//TODO need to make a check that ensures that the quantity is an int greater than 0
			//TODO make javascript stuff
		
		//check to see if quantity is correct
		//TODO this could also be done as javascript
		try {
			quantity = Integer.parseInt(quantityString);
			if(quantity < 0) {
				//TODO output some type of error because you can't have negative shoes
				
			}
			//quantity is good, so we can add it to cart
			cart.addToCart(id, name, price, size, quantity);

		} catch (NumberFormatException e) {
			//TODO tell the user that it needs to be an integer
				//and to do less stupid things
				//WHY MUST YOU DO THIS TO ME, CUSTOMER?
				//
		}
		
		
		
		
		
	}

}
