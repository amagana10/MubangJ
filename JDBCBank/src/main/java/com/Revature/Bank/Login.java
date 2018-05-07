package com.Revature.Bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Revature.Dao.ConnectionUtil;
import com.Revature.Dao.UserDao;
import com.Revature.Dao.UserDaoImpl;
import com.Revature.Exceptions.UserDoesNotExistException;
import com.Revature.Tables.User;

public class Login {
	

	private String username;
	private String password;
	private String superUser;
	
	private User user;	
	private UserDao userDao;
	
	public Login(String username, String password) {
		super();
		
		this.username = username;
		this.password = password;	
		this.userDao = new UserDaoImpl();
		this.user = userDao.getUserByName(username);
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean isLoggedIn() {
				
		try {
			if (user == null) {
				throw new UserDoesNotExistException();
			} 
			
		} catch (UserDoesNotExistException e) {
			e.getMessage();
			return false;
		}
		
		if (!user.getPassword().equals(password)) {
			System.out.println("The username or password you have entered does not exist. Please try again.\n");
			return false;
		}
	
		return true;		
	}
	
	public boolean superUser() {
		this.superUser = user.getSuperUser();
		if (this.superUser == null) {
			return false;
		} 
		
		if (this.superUser.equals("y")) {
			return true;
		}
		
		return false;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public User getUser() {
		return user;
	}

	
}
