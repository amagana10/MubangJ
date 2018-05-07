package com.Revature.Dao;

import java.util.List;

import com.Revature.Tables.Department;


public interface DepartmentDao {
	
	List<Department> getDeparmentById(int departmentId);
	
}
