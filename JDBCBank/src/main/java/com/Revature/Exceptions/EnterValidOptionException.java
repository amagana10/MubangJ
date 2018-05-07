package com.Revature.Exceptions;

public class EnterValidOptionException extends Exception {
	
	public EnterValidOptionException() {
		
		super("Please Fill Out prompt(s) with valid options.\n");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
