package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Cave;
import com.revature.util.ConnectionUtil;

public class CaveDaoImpl implements CaveDao {

	private String filename = "connection.properties";

	@Override
	public List<Cave> getCaves() {
		List<Cave> cl = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// using a Statement - beware SQL injection
			String sql = "SELECT * FROM CAVE";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// move through result set
			while (rs.next()) {
				int id = rs.getInt("CAVE_ID");
				String name = rs.getString("CAVE_NAME");
				int maxBears = rs.getInt("MAX_BEARS");
				cl.add(new Cave(id, name, maxBears));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cl;
	}

	@Override
	public Cave getCaveById(int id) {
		Cave c = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT * FROM CAVE WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
			if (rs.next()) {
				String name = rs.getString("CAVE_NAME");
				int maxBears = rs.getInt("MAX_BEARS");
				c = new Cave(id, name, maxBears);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return c;
	}
	
	public void insertCave(String caveName, int maxBears) {
		Cave c = null;
		PreparedStatement pstmt = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "INSERT INTO CAVE (CAVE_NAME, CAVE_) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, caveName);
			pstmt.setInt(2, maxBears);
			pstmt.executeQuery();
			System.out.println("inserted " + caveName + ", " + maxBears);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCave(int caveId, String caveName, int maxBears) {
		Cave c = null;
		PreparedStatement pstmt = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "UPDATE CAVE SET CAVE_NAME = ?, MAX_BEARS = ? WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, caveName);
			pstmt.setInt(2, maxBears);
			pstmt.setInt(3, caveId);
			pstmt.executeQuery();
			System.out.println("updated " + caveId + ", " + caveName + ", " + maxBears);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCave(int caveId) {
		Cave c = null;
		PreparedStatement pstmt = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "DELETE FROM CAVE WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, caveId);
			pstmt.executeQuery();
			System.out.println("Deleted cave with the id of " + caveId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}