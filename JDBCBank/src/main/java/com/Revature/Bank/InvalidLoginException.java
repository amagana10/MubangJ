package com.Revature.Bank;

public class InvalidLoginException extends Exception {

	public InvalidLoginException() {
		super("Invalid Username or Password");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
