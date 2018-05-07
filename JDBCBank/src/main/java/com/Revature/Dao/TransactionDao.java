package com.Revature.Dao;

import java.util.List;

import com.Revature.Tables.Transaction;


public interface TransactionDao {

	List<Transaction> getTransactionHistory(int userId);
	List<Transaction> getTransactionsByAccountName(String accountName);
	boolean insertTransaction(int userId, String bankAccountName, double amount);
	boolean updateTransaction(int transactionId, int userId, String accountName, double amount);
	boolean deleteTransaction(int transactionId);
	boolean deleteTransactions(int userId);
}
