package com.Revature.Dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Revature.Exceptions.EnterValidOptionException;
import com.Revature.Exceptions.UserAlreadyExistException;
import com.Revature.Exceptions.UserDoesNotExistException;
import com.Revature.Tables.User;


public class UserDaoImpl implements UserDao {
	
	private String filename = "connection.properties";
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT USER_ID, USER_NAME, USER_PASSWORD, SUPERUSER FROM USERS";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String username = rs.getString("USER_NAME");
				String password = rs.getString("USER_PASSWORD");
				String superUser = rs.getString("SUPERUSER");
				users.add(new User(userId, username, password, superUser));
			}
			conn.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public User getUserById(int userId) {
		User user = null;
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			// using a Statement - beware SQL injection
			String sql = "SELECT USER_ID, USER_NAME, USER_PASSWORD, SUPERUSER FROM USERS WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				throw new UserDoesNotExistException();
			}
			
			int userID = rs.getInt("USER_ID");
			String username = rs.getString("USER_NAME");
			String password = rs.getString("USER_PASSWORD");
			String superUser = rs.getString("SUPERUSER");

			user = new User(userID, username, password, superUser);
			
			conn.close();
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
			return user;
		}
		catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	@Override
	public User getUserByName(String username) {
		User user = null;
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			// using a Statement - beware SQL injection
			String sql = "SELECT USER_ID, USER_NAME, USER_PASSWORD, SUPERUSER FROM USERS WHERE USER_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				throw new UserDoesNotExistException();
			}
			// move through result set
			
			int userID = rs.getInt("USER_ID");
			String password = rs.getString("USER_PASSWORD");
			String superUser = rs.getString("SUPERUSER");

			user = new User(userID, username, password, superUser);
			
			conn.close();
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
			return user;
		} catch (IOException | SQLException e2) {
			e2.printStackTrace();
		} 
		return user;	
	}

	@Override
	public boolean insertUser(String username, String password) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			if (getUserByName(username) != null) {
				throw new UserAlreadyExistException();
			}
			
			String sql = "{call insertUserProcedure(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			int i = cs.executeUpdate();
			conn.close();
			if (i > 0) return true;
			
		} catch (UserAlreadyExistException e) {
			System.out.println(e.getMessage());
			return false;
		}
		catch (IOException e2) {
			e2.printStackTrace();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateUser(int userId, String userOrPass, String option) {
		PreparedStatement pstmt = null;
			

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			if ( (option.toLowerCase().equals("username") && getUserByName(userOrPass) != null)) {
				throw new UserAlreadyExistException();
			} else if (getUserById(userId) == null) {
				throw new UserDoesNotExistException();
			}
			
			String sql = null;
			if (option.toLowerCase().equals("password")) {
				sql = "UPDATE USERS SET USER_PASSWORD = ? WHERE USER_ID = ?";
			} else if (option.toLowerCase().equals("username")) {
				sql = "UPDATE USERS SET USER_NAME = ? WHERE USER_ID = ?";
			} 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userOrPass);
			pstmt.setInt(2, userId);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;
			
		
		} catch (UserAlreadyExistException | UserDoesNotExistException e) {
			System.out.println(e.getMessage() + " ( "+ userOrPass +" )");
			return false;
		}
		catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		PreparedStatement pstmt = null;
		
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			if (getUserById(userId) == null) {
				throw new UserDoesNotExistException();
			}
			
			String sql = "DELETE FROM USERS WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int i = pstmt.executeUpdate();
			conn.close();
			
			if (i > 0) return true;

		} catch (UserDoesNotExistException e) {
			e.getMessage();
			return false;
		} catch (IOException | SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}

}
