package com.Revature.Tables;

public class Department {
	
	private String departmentName;
	private double avgSalary;
	private int doesIdExist;
	
	public Department(String departmentName, double avgSalary, int doesIdExist) {
		super();
		this.departmentName = departmentName;
		this.avgSalary = avgSalary;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public double getAvgSalary() {
		return avgSalary;
	}

	public int getDoesIdExist() {
		return doesIdExist;
	}	
		
}
