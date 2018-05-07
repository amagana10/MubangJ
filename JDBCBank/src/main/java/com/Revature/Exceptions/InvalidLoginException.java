package com.Revature.Exceptions;

public class InvalidLoginException extends Exception {

	public InvalidLoginException() {
		super("Invalid Username or Password.\n");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
