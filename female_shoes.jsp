<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,			
				java.util.*,
				utilities.*"
%>
<html>
	<head>
		<title>Women's Shoes</title>
        <link rel="stylesheet" type="text/css" href="custom.css">
	</head>
	<body>
		<jsp:include page="_navigationBar.jsp" />
		<div id="product-index">
            <div id="womens-shoes-index">
                <h1>All Shoes</h1>
                <hr class="separator">
                <table id="womens-shoes-table">
					<tr colspan="3">
               			<%
							int SHOES_PER_ROW = 3;
							int shoeCol = 0;
							DatabaseConnection db = new DatabaseConnection();
					        String query = "SELECT productID, name, price, imagePath FROM product where gender='F'";
							Statement getShoes = db.connection.createStatement();
							ResultSet shoes = getShoes.executeQuery(query);
							while(shoes.next()) {
								int id = shoes.getInt(1);
								String name= shoes.getString(2);
								double price= shoes.getDouble(3);
								String imagePath= shoes.getString(4);
								
								%>
		                        <td>
		                            <a href="Product?id=<%=id%>">
		                                <img class="product-img" src="<%=imagePath %>" alt="<%=name%>">
										<p><%=name %></p>
										<p>$<%=price %></p>
			                        </a>
		                        </td>								
		                        
								<%
								shoeCol++;
								if(shoeCol == SHOES_PER_ROW) {
									shoeCol = 0;
									%>
									</tr>
									<tr colspan="3">
									<%
								}
							}
						%>
                </table>
            </div>
        </div>
	</body>
	
</html>

<%-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Female Shoes</title>
</head>
<body>
	<jsp:include page="_navigationBar.jsp" />
	<h3>Female Shoes</h3>
	<table border=1>
		<tr>
		<%
		int SHOES_PER_ROW = 3;
		int shoeCol = 0;
		DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT productID, name, price, imagePath FROM product where gender='F'";
		Statement getShoes = db.connection.createStatement();
		ResultSet shoes = getShoes.executeQuery(query);
		while(shoes.next()) {
			int id = shoes.getInt(1);
			String name= shoes.getString(2);
			double price= shoes.getDouble(3);
			String imagePath= shoes.getString(4);
			
			%>
			<td>
				<a href="Product?id=<%=id%>">
				<img src="<%=imagePath %>" alt="<%=name%>">
				<p><%=name %></p>
				<p>$<%=price %></p>
				<a>
			</td>
			<%
			shoeCol++;
			if(shoeCol == SHOES_PER_ROW) {
				shoeCol = 0;
				%>
				</tr>
				<tr>
				<%
			}
		}
		%>
		</tr>
	</table>
	
</body>
</html> --%>