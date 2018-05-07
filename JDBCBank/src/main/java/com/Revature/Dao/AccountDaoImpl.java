package com.Revature.Dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.Exceptions.AccountAlreadyExistException;
import com.Revature.Exceptions.AccountDoesNotExistException;
import com.Revature.Exceptions.AccountHasMoreThanZeroDollarsException;
import com.Revature.Exceptions.OverDraftException;
import com.Revature.Tables.Account;
import com.Revature.Tables.User;

public class AccountDaoImpl implements AccountDao {

	private String filename = "connection.properties";

	@Override
	public List<Account> getAccounts(int userId) {
		List<Account> accounts = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT a.USER_ID, a.ACCOUNT_NAME, a.AMOUNT, u.USER_NAME FROM ACCOUNTS a INNER JOIN USERS u ON u.USER_ID = a.USER_ID WHERE a.USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();


			while (rs.next()) {
				String username = rs.getString("USER_NAME");
				String accountName = rs.getString("ACCOUNT_NAME");
				int amount = rs.getInt("AMOUNT");
				int userID = rs.getInt("USER_ID");
				accounts.add(new Account(username, accountName, amount, userID));
			}
			
			conn.close();
		} 
		catch (IOException | SQLException e2) {
			e2.printStackTrace();
		}

		return accounts;
	}

	@Override
	public Account getAccountById(int userId, String accountName) {
		Account account = null;
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			

			String sql = "SELECT a.USER_ID, a.ACCOUNT_NAME, a.AMOUNT, u.USER_NAME "
					+ "FROM ACCOUNTS a "
					+ "INNER JOIN USERS u ON u.USER_ID = a.USER_ID "
					+ "WHERE a.USER_ID = ? AND a.ACCOUNT_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountName);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new AccountDoesNotExistException();
			}
			String username = rs.getString("USER_NAME");
			String nameOfAccount = rs.getString("ACCOUNT_NAME");
			int amount = rs.getInt("AMOUNT");
			int userID = rs.getInt("USER_ID");
			account = new Account(username, nameOfAccount, amount, userID);

			
			conn.close();
		} catch (AccountDoesNotExistException e) {
			System.out.println(e.getMessage());
			return account;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return account;
	}

	@Override
	public boolean insertAccount(int userId, String accountName, double amount) {
		PreparedStatement pstmt = null;
		List<Account> accounts = new ArrayList<>();
		accounts = getAccounts(userId);
		
		try(Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			for (Account account : accounts) {
				if (account.getAccountName().equals(accountName)) {
					throw new AccountAlreadyExistException();
				}
			}
			if (amount < 0) {
				throw new OverDraftException();
			}

			String sql = "INSERT INTO ACCOUNTS (USER_ID, ACCOUNT_NAME) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountName);
			int i = 0;
			i = pstmt.executeUpdate();	
			
			conn.close();
			
			if (i > 0) return true;
			return false;
			
		} catch (AccountAlreadyExistException | OverDraftException e) {
			System.out.println(e.getMessage() + " ( " + accountName + " )");
			return false;
		} catch (IOException | SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAccount(int userId, String accountName, double amount) {
		PreparedStatement pstmt = null;
		Account account = null;
		
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			account = getAccountById(userId, accountName);
			if (account == null) {
				throw new AccountDoesNotExistException();
			}
			
			if ( (amount * -1) > account.getAmount()) {
				throw new OverDraftException();
			}
			// using a Statement - beware SQL injection
			String sql = null;
			sql = "UPDATE ACCOUNTS SET AMOUNT = AMOUNT + ? WHERE USER_ID = ? AND ACCOUNT_NAME = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, userId);
			pstmt.setString(3, accountName);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
		
		} catch (OverDraftException e) {
			System.out.println(e.getMessage());
			
		} catch (AccountDoesNotExistException e2) {
			e2.getMessage();
			
		} catch (IOException | SQLException e3) {
			e3.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean deleteAccount(int userId, String accountName) {
		PreparedStatement pstmt = null;
		Account account = null;
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			account = getAccountById(userId, accountName);
			if (account == null) {
				throw new AccountDoesNotExistException();
			}
			if (account.getAmount() != 0) {
				throw new AccountHasMoreThanZeroDollarsException();
			}
			
			String sql = "DELETE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, accountName);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
			
		} 
		catch (AccountHasMoreThanZeroDollarsException | AccountDoesNotExistException e2) {
			e2.getMessage();
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean superDeleteAccounts(int userId) {
		PreparedStatement pstmt = null;
		Account account = null;
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			
			String sql = "DELETE FROM ACCOUNTS WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
			else {
				throw new AccountDoesNotExistException();
			}
			
		} catch (AccountDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
