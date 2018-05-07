package com.Revature.Exceptions;

public class AccountHasMoreThanZeroDollarsException extends Exception {
	
	public AccountHasMoreThanZeroDollarsException() {
	
		super("The account name you have entered already exist. Please try again.\n");
	}


	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
