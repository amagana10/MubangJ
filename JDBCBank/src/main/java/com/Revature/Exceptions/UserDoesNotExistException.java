package com.Revature.Exceptions;

public class UserDoesNotExistException extends Exception {
	
	public UserDoesNotExistException() {
		super("The username or password you have entered does not exist.\n");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
