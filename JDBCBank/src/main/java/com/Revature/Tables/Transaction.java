package com.Revature.Tables;

public class Transaction {
	
	private int transactionId;
	private String username;
	private String bankAccountName;
	private double amount;
	private String date;
	private int userId;
	
	
	
	public Transaction(int transactionId, String username, String bankAccountName, int amount, String date, int userId) {
		super();
		this.transactionId = transactionId;
		this.username = username;
		this.bankAccountName = bankAccountName;
		this.amount = amount;
		this.date = date;
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
