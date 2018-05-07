package com.Revature.Exceptions;

public class OverDraftException extends Exception {

	public OverDraftException() {
		super("You have attempted to withdraw more money then you have in your account.\n");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
		

}
