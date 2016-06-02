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
	<h3>Shipping Information</h3>
	<table border=1>
<% 
	String shipname = request.getParameter("name");
	String shipstreet = request.getParameter("shipstreet");
	String shipcity = request.getParameter("shipcity");
	String shipstate = request.getParameter("shipstate");
	String shipzip = request.getParameter("shipzip");
	String shipcountry = request.getParameter("shipcountry");
	String email = request.getParameter("email");%>
	<tr><td>Name: <%=shipname %></td></tr>
	<tr><td>Street: <%=shipstreet %></td></tr>
	<tr><td>City: <%=shipcity %></td></tr>
	<tr><td>State: <%=shipstate %></td></tr>
	<tr><td>Zip Code: <%=shipzip %></td></tr>
	<tr><td>Country: <%=shipcountry %></td></tr>
	<tr></tr>
	<tr><td>Email: <%=email %></td></tr>
	</table>
	<h3>Billing Information</h3>
	<table border=1>
<% 
	String name = request.getParameter("name");
	String street = request.getParameter("street");
	String city = request.getParameter("city");
	String state = request.getParameter("state");
	String zip = request.getParameter("zip");
	String country = request.getParameter("country");%>
	<tr><td>Name: <%=name %></td></tr>
	<tr><td>Street: <%=street %></td></tr>
	<tr><td>City: <%=city %></td></tr>
	<tr><td>State: <%=state %></td></tr>
	<tr><td>Zip Code: <%=zip %></td></tr>
	<tr><td>Country: <%=country %></td></tr>
	</table>
	<table border=1>
	<h3>Payment Information</h3>
<% 
	String card = request.getParameter("creditcard");
	String expiration = request.getParameter("expiry");
	String cvc = request.getParameter("code");%>	
	<tr><td>card: <%=card %></td></tr>
	<tr><td>expiration: <%=expiration %></td></tr>
	<tr><td>cvc: <%=cvc %></td></tr>
	</table>
</body>
</html>