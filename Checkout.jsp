<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Please Enter your shipping information</h1>
	<p>
	<form action="/servlets/Checkout" method="post">
	<table>
		<tr><td>Name:</td><td><input type="text" name="name"></td></tr>
		<tr><td>Street:</td><td><input type="text" name="shipstreet"></td></tr>
		<tr><td>City:</td><td><input type="text" name="shipcity"></td></tr>
		<tr><td>State:</td><td><input type="text" name="shipstate"></td></tr>
		<tr><td>Zip Code:</td><td><input type="text" name="shipzip"></td></tr>
		<tr><td>Country:</td><td><input type="text" name="shipcountry"></td></tr>
		<tr></tr>
		<tr><td>Email:</td><td><input type="text" name="email"></td></tr>
	</table>
	<p>
	<h1>Please Enter your billing information</h1>
	<table>
		<tr><td>Street:</td><td><input type="text" name="street"></td></tr>
		<tr><td>City:</td><td><input type="text" name="city"></td></tr>
		<tr><td>State:</td><td><input type="text" name="state"></td></tr>
		<tr><td>Zip Code:</td><td><input type="text" name="zip"></td></tr>
		<tr><td>Country:</td><td><input type="text" name="country"></td></tr>
		<tr></tr>
		<tr><td>Credit Card Number:</td>
			<td><input type="text" name="creditcard"></td></tr>
		<tr><td>Expiration Date:</td>
			<td><input type="text" name="expiry"></td></tr>
		<tr><td>CVC Code:</td>
			<td><input type="text" name="code"></td></tr>
	</table>
	<p>
	<input type="submit" value="Complete Order">
	</form>
</body>
</html>
