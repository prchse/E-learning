package com.elearning.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    Connection con = null;

	 void connect() {

		String url = "jdbc:oracle:thin:@//localhost:1521/XE ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // step-1 register
																// driver
			con = DriverManager.getConnection(url, "elearning", "elearning"); // step-2
			//System.out.println("Connected hello");
		} catch (Exception e) {
			System.out.println("Error connecting...");
			e.printStackTrace();
		}

	}

	void disconnect() {

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
}
