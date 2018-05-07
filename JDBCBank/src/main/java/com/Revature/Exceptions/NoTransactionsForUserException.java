package com.Revature.Exceptions;

public class NoTransactionsForUserException extends Exception {
	
	public NoTransactionsForUserException() {
		super("The specified user does not have any transaction history.");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}	
}
