package com.Revature.Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

import com.Revature.Dao.AccountDao;
import com.Revature.Dao.AccountDaoImpl;
import com.Revature.Dao.TransactionDao;
import com.Revature.Dao.TransactionDaoImpl;
import com.Revature.Dao.UserDao;
import com.Revature.Tables.Account;
import com.Revature.Tables.Transaction;
import com.Revature.Tables.User;

public class SimpleUser {
	
	private Login login;
	private User user;
	private UserDao userDao;
	private AccountDao accountDao = new AccountDaoImpl();
	private TransactionDao transactionDao = new TransactionDaoImpl();
	
	public SimpleUser(Login login) {
		this.login = login;
		userDao = login.getUserDao();
		user = login.getUser();
	}

	public void viewAllAccounts() {
		 DecimalFormat df = new DecimalFormat("0.00");
		
		List<Account> accounts = accountDao.getAccounts(user.getId());
		for (Account account: accounts) {
			System.out.println("\tYou have " + "$" + df.format(account.getAmount()) + " in " + account.getAccountName() + "\n");
		}		
	}
	
	public boolean createAccount(String accountName) {
		
		boolean inserted = accountDao.insertAccount(user.getId(), accountName, 0.00);
		
		if(inserted) {
			System.out.println("Created New Account: " + accountName);
			transactionDao.insertTransaction(user.getId(), accountName, 0.00);
		} else {
			System.out.println("Error creating account: " + accountName);
		}
		return inserted;
	}
	
	public boolean deleteAccount(String accountName) {

		boolean didItWork = accountDao.deleteAccount(user.getId(), accountName);
		
		if (didItWork) {
			System.out.println("You have deleted " + accountName +"\n");
			return true;
		} else {
			System.out.println("Could not delete this account.\n");
		}
		
		return false;
	}
	
	public boolean withdraw(String accountName, double amount) {
		 DecimalFormat df = new DecimalFormat("0.00");

		if (amount > 0) 
			amount *= -1;
		
		boolean worked = accountDao.updateAccount(user.getId(), accountName, amount);
		
		if (worked) {
			transactionDao.insertTransaction(user.getId(), accountName, amount);
			System.out.println("You have withdrawn " + df.format(amount) + " from " + accountName + "\n");
			return true;
		}
		
		return false;
	}
	
	public boolean deposit(String accountName, double amount) {
		 DecimalFormat df = new DecimalFormat("0.00");

		if (amount < 0) {
			System.out.println("You cannot deposit negative funds\n");
			return false;
		}
		
		boolean worked = accountDao.updateAccount(user.getId(), accountName, amount);
		
		if (worked) {
			transactionDao.insertTransaction(user.getId(), accountName, amount);
			System.out.println("You have deposited " + df.format(amount) + " from " + accountName + "\n");
			return true;
		}
		
		return false;
	}
	
	public boolean accessAccount(String accountName) {
		DecimalFormat df = new DecimalFormat("0.00");

		
		Account account = accountDao.getAccountById(user.getId(), accountName);
		
		if (account != null) {
			
			System.out.println("You are now viewing: " + accountName + "\n");
			System.out.println("\tAmount: " + df.format(account.getAmount()) + "\n");
			List<Transaction> transactions = transactionDao.getTransactionsByAccountName(accountName);
			if (!transactions.isEmpty()) {
				System.out.println("Transaction History: \n");
				for (Transaction transaction : transactions) {
					int counter = 1;
					System.out.println("\tId: " + transaction.getTransactionId() + " | Account Name: " + transaction.getBankAccountName() + " | Amount: " + String.format("%.2f", transaction.getAmount()) + " | Date/Time: " + transaction.getDate() + "\n");
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public void viewTransactionHistory() {
		DecimalFormat df = new DecimalFormat("0.00");

		List<Transaction> transactions = transactionDao.getTransactionHistory(user.getId());
		if (!transactions.isEmpty()) {
			System.out.println("Transaction History: \n");
			for (Transaction transaction : transactions) {
				System.out.println("\tId: " + transaction.getTransactionId() + " | Account Name: " + transaction.getBankAccountName() + " | Amount: " + df.format(transaction.getAmount()) + " | Date/Time: " + transaction.getDate() + "\n");
			}
		}
	}
		
}

