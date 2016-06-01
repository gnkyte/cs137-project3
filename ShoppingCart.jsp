<%@ page language="java" import="utilities.*,java.util.*,java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart Contents</title>
</head>
<%

if(session.getAttribute("cart")==null) {
	session.setAttribute("cart", new ShoppingCart());
}
ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

ArrayList<Shoe> items = cart.getShoes();
if(cart.isEmpty()){
	out.println("<h3>The shopping cart is empty.</h3>");
}
else{
%>
<body>
<br>
<table border=4>
<tr><th>Item</th><th>Quantity</th><th>Price</th></tr>
<%
	NumberFormat c = NumberFormat.getCurrencyInstance();
	for(int i = 0; i < cart.getNumItems(); i++){
		out.print("<tr><td>");
		out.print(items.get(i).getName());
		out.print("</td><td>");
		out.print(items.get(i).getQuantity());
		out.print("</td><td>");
		out.print(c.format(items.get(i).getPrice()));
		
		out.println("</td><td>" + "<a href=\"/servlets/RemoveItem?item=" + items.get(i).getID() + "\">Remove</a></td></tr>");
		
	}
}
%></table>
</body>
</html>
