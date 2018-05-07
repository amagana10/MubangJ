package com.Revature.Exceptions;

public class TransactionDoesNotExistException extends Exception {
	
	public TransactionDoesNotExistException() {
		
		super("The transaction you have entered does not exist.\n");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
