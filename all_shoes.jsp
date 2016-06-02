<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,			
				java.util.*,
				utilities.*"
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>All Shoes</title>
        <link rel="stylesheet" type="text/css" href="custom.css">
	</head>
	<body>
		<jsp:include page="_navigationBar.jsp" />
		<jsp:include page="_all_shoes_table.jsp" />
	</body>
</html>

