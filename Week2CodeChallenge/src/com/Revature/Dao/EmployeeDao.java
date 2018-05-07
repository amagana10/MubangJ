package com.Revature.Dao;

import java.util.List;

import com.Revature.Tables.Employee;


public interface EmployeeDao {
	
	List<Employee> getAccounts(int userId);
	Employee getEmployyById(int userId);
	
}
