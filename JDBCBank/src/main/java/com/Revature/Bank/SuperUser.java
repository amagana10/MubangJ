package com.Revature.Bank;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.Revature.Dao.AccountDao;
import com.Revature.Dao.AccountDaoImpl;
import com.Revature.Dao.TransactionDao;
import com.Revature.Dao.TransactionDaoImpl;
import com.Revature.Dao.UserDao;
import com.Revature.Tables.User;

public class SuperUser {
	
	Login login;
	UserDao userDao;
	AccountDao accountDao = new AccountDaoImpl();
	TransactionDao transactionDao = new TransactionDaoImpl();
	
	public SuperUser(Login login) {
		this.login = login;
		this.userDao = login.getUserDao();
	}
	
	public void viewAllUsers() {
		List<User> users = userDao.getUsers();
		System.out.println("Users: ");
		for (User user : users) 
		{
			System.out.println("\tUSER_ID: " + user.getId() + " | USER_NAME: " + user.getUsername() + 
					" | USER_PASSWORD: " + user.getPassword() + " | SUPERUSER: " + user.getSuperUser() + "\n");
		}
	}
	
	public boolean createUser(String username, String password) {
		
		try {
			boolean worked = userDao.insertUser(username, password);
			if(worked) {
				return true;
			} 
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Could not add user. Make sure username / password are valid and are not in the database");;
		}
		return false;
		
	}
	
	public boolean updateUser(int userId, String userOrPass, String option) {
		boolean worked = userDao.updateUser(userId, userOrPass, option);
		if(worked) {
			System.out.println("\nUser with id: " + userId + " has been updated. ");
			return true;
		}
		System.out.println("\nFailed to upate user");
		return false;
	}
	
	public boolean deleteUser(String accountName, int userId) {
		
		deleteTransactions(userId);
		deleteAccounts(userId);
		boolean deleteUser = userDao.deleteUser(userId);
		
		if (deleteUser) {
			System.out.println("User with id: " + userId + " has been deleted");
			return true;
		}

		return false;
	}
	
	private boolean deleteTransactions(int userId) {
		return transactionDao.deleteTransactions(userId);
	}
	
	private boolean deleteAccounts(int userId) {
		return accountDao.superDeleteAccounts(userId);
	}
	
}
