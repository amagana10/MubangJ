package com.Revature.Bank;

import java.util.Arrays;
import java.util.List;

import com.Revature.Dao.UserDaoImpl;
import com.Revature.Exceptions.UserDoesNotExistException;
import com.Revature.Tables.User;

public class RegisterUser {

	private String username;
	private String password;
	private UserDaoImpl userDao = new UserDaoImpl();
	private User user;
	
	public RegisterUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
		
	public boolean userExist() {
		
		
		if(userDao.getUserByName(username) == null) {
			userDao.insertUser(username, password);
			System.out.println("Account Successfully Created\n");
			return false;
		}
		
		if(userDao.getUserByName(username) != null) {
			System.out.println("The user already exist\n");
			return true;
		}
		return false;

	}

}
