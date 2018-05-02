package com.Revature.Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleUser {
	
	private String username;
	private String password;
	
//	Connection conn = DriverManager.getConnection(password, password, password);
//	
//	Statement stmt = conn.createStatement();
//	ResultSet userInfo = stmt.executeQuery("SELECT UserId, username FROM Users WHERE username =" + username + ", AND password=" + password);
//	ResultSet accountInfo = stmt.executeQuery("SELECT AccountName, money FROM Users WHERE UserId =" + userInfo.getString(0));

	
	public SimpleUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void viewAllAccounts() {
//		TODO
		// query accounts database for all accounts user owns
		//do for loop on account and print out account.accountName
		
		System.out.println("You have " + "amount" + " in this account");
		System.out.println("\n");
	}
	
	public boolean createAccount(String accountName) {
//		TODO
		System.out.println("");
		return false;
	}
	
	public boolean deleteAccount(String accountName) {
//		TODO
		// check if there is no money in account
		
		System.out.println("You have deleted " + accountName +"\n");
		return false;
	}
	
	public boolean withdraw(String accountName, int amount) {
//		TODO
//		if (amount > accountInfo.get("money")) {
//			System.out.println("The amount you want to withdraw, exceeds the amount you have in your bank account.");
//		}
		
//		stmt.executeQuery("UPDATE Accounts (money) SET (money = money - " + amount + ") WHERE UserId = " + userInfo.("UserId") + " AND AccountName = " + accountName);
		
		System.out.println("\\033[31m You have withdrawn " + amount + "\n");
		return false;
	}
	
	public boolean deposit(String accountName, int amount) {
//		TODO
		
//		stmt.executeQuery("UPDATE Accounts (money) SET (money = money + " + amount + ") WHERE UserId = " + userInfo.("UserId") + " AND AccountName = " + accountName);

		System.out.println("\\033[32m You have deposited " + amount + "\n");
		return false;
	}
	
	public boolean switchAccounts() {
//		TODO
		System.out.println("");
		return false;
	}
	
	public boolean logOut(String yesOrNo) {
//		TODO
		
		return false;
	}
		
}

//System.out.println("\033[0m BLACK");
//System.out.println("\033[31m RED");
//System.out.println("\033[32m GREEN");
//System.out.println("\033[33m YELLOW");
//System.out.println("\033[34m BLUE");
//System.out.println("\033[35m MAGENTA");
//System.out.println("\033[36m CYAN");
//System.out.println("\033[37m WHITE");
