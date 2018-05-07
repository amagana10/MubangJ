package com.Revature.CodeChallengeTwo;

import java.util.List;

import com.Revature.Dao.DepartmentDao;
import com.Revature.Dao.DepartmentDaoImpl;
import com.Revature.Tables.Department;

public class Main {
	
	public static void main(String[] args) {
		
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		List<Department> departments = departmentDao.getDeparmentById(3);
		
		for (Department department : departments) {
			if (department.getDoesIdExist() == 1) {
				System.out.println("Department Name: " + department.getDepartmentName() + " | Average Salary: " + department.getAvgSalary());
			} else {
				System.out.println("The Id provided does not exist.");
			}
		}
	}
}
