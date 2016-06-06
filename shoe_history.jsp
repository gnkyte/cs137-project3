<%@ page language="java" import="utilities.*,java.util.*,java.text.*, java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<%
if(session.getAttribute("history")==null) {
	session.setAttribute("history", new ShoeHistoryContainer());
}
ShoeHistoryContainer history= (ShoeHistoryContainer) session.getAttribute("history");
if(history.isEmpty()){
	%>
	<h3>Your viewing history is empty. Go look at some shoes!</h3>
	<%
}
else{
	Queue<ShoeHistory> shoes = history.getShoes();
%>
<br>
<h3>Viewing History</h3>
<table border=1>
	<tr>
<%
	ShoeHistory shoe;
	
	for(int i = 0; i < history.getCount(); ++i) {
		shoe = shoes.poll();
		
		String name = shoe.getName();
		String imagePath = shoe.getImagePath();
		%>
		
		<td>
		<img class="product-img" alt="<%=name %>" src="<%=imagePath%>">
		<br><%=name %>
		</td>
		<%		
		shoes.add(shoe);
	}
}
%> 
	</tr>
</table>
