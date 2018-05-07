package com.Revature.Dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.Revature.Tables.User;


public interface UserDao {
	
	List<User> getUsers();
	User getUserById(int userId);
	User getUserByName(String username);
	boolean insertUser(String username, String password) throws SQLIntegrityConstraintViolationException;
	boolean updateUser(int userId, String userOrPass, String option);
	boolean deleteUser(int userId);
	
}
