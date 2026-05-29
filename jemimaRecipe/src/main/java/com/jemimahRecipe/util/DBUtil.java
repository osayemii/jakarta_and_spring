package com.jemimahRecipe.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jemimahRecipe?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String USER = "root";
	private static final String Password = "";
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, Password);
	}
}