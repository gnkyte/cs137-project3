package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
	private static final String username="inf124grp18";
	private static final String password="Gewr8R-w";
	private static final String loginURL="jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124grp18";
	
	public Connection connection;
	
	public DatabaseConnection() {
		connect();
	}
//	public DatabaseConnection()
//	{
//		username = "root";
//		password = "root";
//		loginURL = "jdbc:mysql://localhost:3306/inf124grp18";
//		connect();
//	}
	
	public void connect()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(loginURL, username, password);
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

