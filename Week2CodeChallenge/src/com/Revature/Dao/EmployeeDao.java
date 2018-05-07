package com.Revature.Dao;

import java.util.List;

import com.Revature.Tables.Employee;


public interface EmployeeDao {
	
	List<Employee> getAllEmployees();
	Employee getEmployeesByDeparmentId(int departmentId);
	
}
