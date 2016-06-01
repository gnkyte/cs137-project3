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
		
		header.append( activePage.equals("index") ? "<li class=\"active\"><a href=\"HomepageDisplay\">Home</a></li>" : "<li><a href=\"HomepageDisplay\">Home</a></li>");
		header.append( activePage.equals("all") ? "<li class=\"active\"><a href=\"HomepageDisplay\">All Shoes</a></li>" : "<li><a href=\"HomepageDisplay\">All Shoes</a></li>");
		header.append( activePage.equals("men") ? "<li class=\"active\"><a href=\"HomepageDisplay\">Men's Shoes</a></li>" : "<li><a href=\"HomepageDisplay\">Men's Shoes</a></li>");
		header.append( activePage.equals("women") ? "<li class=\"active\"><a href=\"HomepageDisplay\">Women's Shoes</a></li>" : "<li><a href=\"HomepageDisplay\">Women's Shoes</a></li>");
        
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
