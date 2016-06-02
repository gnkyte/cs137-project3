<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="utilities.ShoppingCart, utilities.ShoeHistoryContainer"%>

<%
	//make new cart and history in session if necessary
	if(session.getAttribute("cart")==null) {
		session.setAttribute("cart", new ShoppingCart());
	}
	if(session.getAttribute("history")==null) {
		session.setAttribute("history", new ShoeHistoryContainer());
	}
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Secret Life of Shoes: Homepage</title>
		<link rel="stylesheet" type="text/css" href="custom.css">
	</head>
	<body>
		<jsp:include page="_navigationBar.jsp" />
		<h1 style="text-align: center">Welcome to Secret Life of Shoes!</h1>
		<h2 style="text-align: center">Your one-stop shop for shoes galore!</h2>
	    <div id="slider">
            <a href="all_shoe.html"><img src="img/slide0.jpg" alt="jumbotron.jpg" /></a>
    	</div>
        <hr align="center" size="1rem" width="30%"/>
		<h3>All Shoes</h3>
		<jsp:include page="_all_shoes_table.jsp" />
		<jsp:include page="shoe_history.jsp" />
	</body>
</html>