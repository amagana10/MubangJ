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
		List<User> users = userDao.getUsers();
		for (User user : users) {
			if(user.getUsername().equals(username)) {
				return false;
			}
		}
		
		userDao.insertUser(username, password);
		return false;
//		user = userDao.getUserByName(username);
//		
//		if (user == null) {
//			return true;
//		}
//		return false;
	}
	
	public String registerResult() {
		if (userExist()) {
			return "Account Successfully Created!\n";
		} else {
			return "The user already exist";
		}
		
	}
}
