package com.app.utils;

import java.sql.*;

public class DBUtils {
	private static Connection connection;
	

	public static void openConnection(String DB_URL, String USER_NAME, 
			String PASSWORD) throws SQLException { 
			
		connection = DriverManager.getConnection(DB_URL,USER_NAME, PASSWORD);				
	}
	//add a static method to close connection
	public static void closeConnection() throws SQLException{
		if(connection != null)
			connection.close();
		System.out.println("db cn closed !");
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
	
	public static void closeConnection1() throws SQLException {
		if (connection != null)
			connection.close();
		System.out.println("db cn closed !");
	}
}
