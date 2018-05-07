package com.Revature.Exceptions;

public class UserAlreadyExistException extends Exception {
	
	public UserAlreadyExistException() {
		super("The username already exists. Please try another username.\n");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
