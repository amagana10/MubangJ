package com.Revature.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.Revature.Exceptions.AccountHasNoTransactionHistoryException;
import com.Revature.Exceptions.NoTransactionsForUserException;
import com.Revature.Exceptions.TransactionDoesNotExistException;
import com.Revature.Exceptions.UserDoesNotExistException;
import com.Revature.Tables.Account;
import com.Revature.Tables.Transaction;

public class TransactionDaoImpl implements TransactionDao {
	
	private String filename = "connection.properties";
	
	@Override
	public List<Transaction> getTransactionHistory(int userId) {
		List<Transaction> transactions = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			String sql = "SELECT t.TRANSACTION_ID, u.USER_NAME, t.USER_ID, t.BANK_ACCOUNT_ID, t.AMOUNT, t.TRANSACTION_DATE " + 
					"FROM TRANSACTIONS t " + 
					"INNER JOIN USERS u ON u.USER_ID = t.USER_ID"
					+ " WHERE t.USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int transactionId = rs.getInt("TRANSACTION_ID");
				String username = rs.getString("USER_NAME");
				String bankAccountName = rs.getString("BANK_ACCOUNT_ID");
				int amount = rs.getInt("AMOUNT");
				int userID = rs.getInt("USER_ID");
				Timestamp date = rs.getTimestamp("TRANSACTION_DATE");
				transactions.add(new Transaction(transactionId, username, bankAccountName, amount, date.toString(), userID));
			}
			conn.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		return transactions;
	}

	@Override
	public List<Transaction> getTransactionsByAccountName(String accountName) {
		List<Transaction> transactions = new ArrayList<>();
		PreparedStatement pstmt = null;		

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			String sql = "SELECT t.TRANSACTION_ID, u.USER_NAME, t.USER_ID, t.BANK_ACCOUNT_ID, t.AMOUNT, t.TRANSACTION_DATE " + 
					"FROM TRANSACTIONS t " + 
					"INNER JOIN USERS u ON u.USER_ID = t.USER_ID"
					+ " WHERE t.BANK_ACCOUNT_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int transactionId = rs.getInt("TRANSACTION_ID");
				String username = rs.getString("USER_NAME");
				String bankAccountName = rs.getString("BANK_ACCOUNT_ID");
				int amount = rs.getInt("AMOUNT");
				int userID = rs.getInt("USER_ID");
				Timestamp date = rs.getTimestamp("TRANSACTION_DATE");
				transactions.add(new Transaction(transactionId, username, bankAccountName, amount, date.toString(), userID));

			}
			
			if (transactions.isEmpty()) {
				throw new AccountHasNoTransactionHistoryException();
			}
			
			conn.close();
		} catch (AccountHasNoTransactionHistoryException e) {
			System.out.println(e.getMessage() + " ( " + accountName + " )\n");
		} catch (IOException | SQLException e2) {
			e2.printStackTrace();
		} 
		
		return transactions;
	}

	@Override
	public boolean insertTransaction(int userId, String accountName, double amount) {
		PreparedStatement pstmt = null;
		UserDao user = new UserDaoImpl();
		try(Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			if (user.getUserById(userId) == null) {
				throw new UserDoesNotExistException();
			}
			String sql = "INSERT INTO TRANSACTIONS (USER_ID, BANK_ACCOUNT_ID, AMOUNT) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountName);
			pstmt.setDouble(3, amount);

			int i = 0;
			i = pstmt.executeUpdate();	
			
			conn.close();
			
			if (i > 0) return true;
			
		} catch(UserDoesNotExistException e) {
			e.getMessage();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateTransaction(int transactionId, int userId, String accountName, double amount) {
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			String sql = null;
			sql = "UPDATE TRANSACTIONS SET USER_ID = ?,  BANK_ACCOUNT_ID = ?, AMOUNT = ?  WHERE TRANSACTION_ID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountName);
			pstmt.setDouble(3, amount);
			pstmt.setInt(4, transactionId);

			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
			else throw new TransactionDoesNotExistException();
		
		} catch (TransactionDoesNotExistException e) {
			e.getMessage();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean deleteTransaction(int transactionId) {
		PreparedStatement pstmt = null;
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "DELETE FROM TRANSACTIONS WHERE TRANSACTION_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, transactionId);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
			else {
				throw new TransactionDoesNotExistException();
			}

		} catch(TransactionDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTransactions(int userId) {
		
		PreparedStatement pstmt = null;
		
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "DELETE FROM TRANSACTIONS WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
			else {
				throw new NoTransactionsForUserException();
			}

		} catch (NoTransactionsForUserException e) {
			System.out.println(e.getMessage());
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
