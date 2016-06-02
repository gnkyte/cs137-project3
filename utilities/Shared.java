package utilities;

public class Shared 
{
	public static String getHeader(String activePage)
	{
		StringBuilder header = new StringBuilder();
		header.append("<div id=\"navbar-container\">");
		header.append("    <nav class=\"main-navbar\">");
		header.append("        <ul id=\"menubar\">");
		header.append("            <li class=\"navbar-logo\"><a href=\"index.html\"><img src=\"img/logo.png\" alt=\"logo\"/></a></li>");
		
		header.append( activePage.equals("index") ? "<li class=\"active\"><a href=\"index.jsp\">Home</a></li>" : "<li><a href=\"index.jsp\">Home</a></li>");
		header.append( activePage.equals("all") ? "<li class=\"active\"><a href=\"all_shoes.jsp\">All Shoes</a></li>" : "<li><a href=\"all_shoes.jsp\">All Shoes</a></li>");
		header.append( activePage.equals("M") ? "<li class=\"active\"><a href=\"male_shoes.jsp\">Men's Shoes</a></li>" : "<li><a href=\"male_shoes.jsp\">Men's Shoes</a></li>");
		header.append( activePage.equals("F") ? "<li class=\"active\"><a href=\"female_shoes.jsp\">Women's Shoes</a></li>" : "<li><a href=\"female_shoes.jsp\">Women's Shoes</a></li>");
        
		header.append("        </ul>");    
		header.append("        <ul id=\"menubar-right\">");
		header.append("            <li><a href=\"aboutMe.html\">About Us</a></li>");
		header.append("        </ul>");
		header.append("        <br />");
		header.append("    </nav>");
		header.append("</div>");
		
		return header.toString();
	}
}
