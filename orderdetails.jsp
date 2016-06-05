<%@ page language="java" import = "utilities.*,java.util.*,java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Details</title>
</head>
<body>
	<h1>Thank you for your order!</h1>
	<h3>Order Information</h3>
	<table border=1>
	<tr><th>Item </th><th>Quantity </th><th>Price </th></tr>
<%
	ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
	ArrayList<Shoe> items = cart.getShoes();
	NumberFormat c = NumberFormat.getCurrencyInstance();
	for(int i = 0; i < cart.getUniqueTotal(); i++)
	{
		out.print("<tr><td>");
		out.print(items.get(i).getName());
		out.print("</td><td>");
		out.print(items.get(i).getQuantity());
		out.print("</td><td>");
		out.print(c.format((items.get(i).getPrice())*(items.get(i).getQuantity())));
	}
%></table>
	<h3>Customer Information</h3>
<% 
	Purchase order = (Purchase)session.getAttribute("purchase");
	out.print("Name: ");
	out.print(order.getName());
	out.print("<br>");
	out.print("Billing Address: ");
	out.print(order.getBill());
	out.print("<br>");
	out.print("Shipping Address: ");
	out.print(order.getShip());
	out.print("<br>");
	out.print("Credit Card Used: ");
	out.print(order.getCredit());
	out.print("<br>");
	out.print("Email: ");
	out.print(order.getEmail());
	out.print("<br>");
	
	session.setAttribute("cart", null);
	session.setAttribute("purchase", null);
%>
</body>
</html>
