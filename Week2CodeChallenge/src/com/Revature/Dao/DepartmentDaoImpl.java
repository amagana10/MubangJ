package com.Revature.Dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.CodeChallengeTwo.ConnectionUtil;
import com.Revature.Tables.Department;
import com.Revature.Tables.Employee;

public class DepartmentDaoImpl implements DepartmentDao {

	private String filename = "connection.properties";

	@Override
	public List<Department> getDeparmentById(int departmentId) {
		List<Department> departments = new ArrayList<>();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			
			String sql = "{call SP_GIVE_RAISE(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, departmentId);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				double avgSalary = rs.getDouble("avgSalary");
				int doesIdExist = rs.getInt("doesIdExist");
				String departmentName = rs.getString("departmentName");
				departments.add(new Department(departmentName, avgSalary, doesIdExist));
			}
			conn.close();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return departments;
	}	
	
}
