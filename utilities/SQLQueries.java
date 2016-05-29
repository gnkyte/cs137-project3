package utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SQLQueries {
	private Connection conn = null;
	private static Statement stmt = null;
	private ResultSet rs = null;
	
	public static boolean inStock(int id, int size, int quantity, Connection c){
		ResultSet rs = null;
		int stock = 0;
		try{
			String query = "SELECT * from stock WHERE id = ? AND size = ?";
			PreparedStatement prepStmt = c.prepareStatement(query);
			String i = Integer.toString(id);
			String s = Integer.toString(size);
			prepStmt.setString(1, i);
			prepStmt.setString(2, s);
			rs = prepStmt.executeQuery();
			stock = rs.getInt("quantity");
		}catch(SQLException e){
			System.out.println(e);
			e.printStackTrace();
		}
		return (stock >= quantity);
	}
	
}
