package com.Revature.Bank;

public class RegisterUser {
	
//	Connection conn = DriverManager.getConnection(password, password, password);
//	
//	Statement stmt = conn.createStatement();
//	ResultSet registerUser = stmt.executeQuery("INSERT INO USERS (username, password);

	String username;
	String password;
	
	public RegisterUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	// TODO write method that checks if username already exists
	
	public boolean userExist() {
		return false;
	}
	
	public String registerResult() {
		if (userExist()) {
			return "Account Successfully Created!\n";
		} else {
			return "The user already exist";
		}
		
	}
}
