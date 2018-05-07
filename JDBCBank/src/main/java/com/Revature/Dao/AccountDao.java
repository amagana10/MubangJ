package com.Revature.Dao;

import java.util.List;

import com.Revature.Tables.Account;

public interface AccountDao {
	
	List<Account> getAccounts(int userId);
	Account getAccountById(int userId, String accountName);
	boolean insertAccount(int userId, String accountName, double amount);
	boolean updateAccount(int userId,String accountName, double amount);
	boolean deleteAccount(int userId, String accountName);
	boolean superDeleteAccounts(int userId);
	
}
