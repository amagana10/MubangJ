package com.Revature.Tables;

public class Account {


	private String username;
	private String accountName;
	private double amount;
	private int userId;
	
	public Account(String username, String accountName, int amount, int userId) {
		super();
		this.username = username;
		this.accountName = accountName;
		this.amount = amount;
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
