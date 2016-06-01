<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,			
				java.util.*,
				utilities.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<table border=1>
		<tr>
		<%
		int SHOES_PER_ROW = 3;
		int shoeCol = 0;
		DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT productID, name, price, imagePath FROM product";
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