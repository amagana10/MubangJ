package com.Revature.Bank;

public class SuperUser {
	
	Login login;
	
	public SuperUser(Login login) {
		this.login = login;
	}
	
	public String getUsername() {
		return login.getUsernameById();
	}
	
	// view, create, update, and delete all users
	
}
