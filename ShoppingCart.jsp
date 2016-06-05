<%@ page language="java" import="utilities.*,java.util.*,java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart Contents</title>
<link rel="stylesheet" type="text/css" href="custom.css">
</head>
<body>
	<jsp:include page="_navigationBar.jsp" />
	<div id="shoe-description-container">
	
<%
if(session.getAttribute("cart")==null) {
	session.setAttribute("cart", new ShoppingCart());
}
ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
ArrayList<Shoe> items = cart.getShoes();
if(cart.isEmpty()){
	out.println("<h3 style='padding-top: 1rem; padding-bottom: 1rem;'>Your shopping cart is empty.</h3>");
}
else{
%>

<table id="cart-table" border=4>
<tr><th>Item</th><th>Quantity</th><th>Price</th></tr>
	<%
		NumberFormat c = NumberFormat.getCurrencyInstance();
		for(int i = 0; i < cart.getUniqueTotal(); i++){
			out.print("<tr><td>");
			out.print(items.get(i).getName());
			out.print("</td><td>");
			out.print(items.get(i).getQuantity());
			out.print("</td><td>");
			out.print(c.format(items.get(i).getPrice()));
			
			out.println("</td></tr>");
			out.println("</table>");
			out.println("<br /><br />");
			out.println("<a href='Checkout.jsp'><input type='button' id='continue-checkout-button' value='Continue Checkout'></a>");
		}
	}
	%>
	
</div>
</body>
</html>