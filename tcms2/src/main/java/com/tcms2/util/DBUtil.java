package com.tcms2.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	private static final String URL =
		    "jdbc:mysql://localhost:3306/students_center?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "admin"; // change if needed
    private static final String PASS = "root"; // change to your MySQL root password

    public static Connection getConnection() throws Exception {
        // Load MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}