package com.booking.DButils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;
	  
	  public static void openConnection(String DB_URL, String USER_NAME, 
				String PASSWORD) throws SQLException
	  {
		  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iacsd_mar24", "root", "iacsd");
	  }
	
	  public static Connection getConnection()
	  {
		  return connection;
	  }
	  
	  public static void closeConnection() throws SQLException {
			if (connection != null)
				connection.close();
			System.out.println("db cn closed !");
		}

}
