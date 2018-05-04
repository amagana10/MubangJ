package com.Revature.Bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login implements LoginDao {
	

	private String username;
	private String password;
	private int userId;
	private String superUser;
	private String filename = "connection.properties";
	
	
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isLoggedIn() {
		PreparedStatement pstmt = null;
		ResultSet userInfo = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT USER_ID, USER_NAME, USER_PASSWORD, SUPERUSER FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,this.username);
			pstmt.setString(2, this.password);
			userInfo = pstmt.executeQuery();
			
			if (userInfo.next()) {
				this.username = userInfo.getString("USER_NAME");
				this.userId = userInfo.getInt("USER_ID");
				this.superUser = userInfo.getString("SUPERUSER");
				return true;
			} else {
				return false;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public int getUsernameById() {
		
		return this.userId;
	}	
	
	@Override
	public boolean superUser() {
		if (this.superUser.equals("n")) return true;
		return false;
	}



	

	
	
}
