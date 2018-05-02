package com.Revature.Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
	

	private String username;
	private String password;
	private final String QUERY_USERS_TABLE = "SELECT UserId, username,superUser FROM Users WHERE username =" + username + ", AND password=" + password;
	
	
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//TODO
	private Connection conn;
//	try {
//		conn = DriverManager.getConnection(password, password, password);	
//		Statement stmt = conn.createStatement();
//		ResultSet userInfo = stmt.executeQuery("SELECT UserId, username,superUser FROM Users WHERE username =" + username + ", AND password=" + password);
//		
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}

	

	public boolean superUser() {
//		TODO
//		return superUser;
		return false;
	}


	public boolean loggedIn() {
//	TODO	
//		return (userInfo.hasNext()) ? true : false;
		return false;
	}
	

	
	
}
