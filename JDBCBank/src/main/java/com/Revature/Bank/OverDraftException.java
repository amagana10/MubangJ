package com.Revature.Bank;

public class OverDraftException extends Exception {

	public OverDraftException() {
		super("You have attempted to withdraw more money then you have in your account");
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
		

}
