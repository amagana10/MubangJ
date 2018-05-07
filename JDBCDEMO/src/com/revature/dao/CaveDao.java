package com.revature.dao;

import java.util.List;

import com.revature.domain.Cave;

public interface CaveDao {
	
	List<Cave> getCaves();
	Cave getCaveById(int id);
	void insertCave(String caveName, int maxBears);
	void updateCave(int caveId, String caveName, int maxBears);
}