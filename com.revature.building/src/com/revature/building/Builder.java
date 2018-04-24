package com.revature.building;

public class Builder extends Worker {
	
	private int numberOfWalls;
	
	public Builder(int numberOfWalls) {
		super();
		if (numberOfWalls > 4) numberOfWalls = 4;
		this.numberOfWalls = numberOfWalls;
	}

	@Override
	boolean dojob() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.numberOfWalls; i++) {
			System.out.println("Built " + i + " Wall");
		}
		
		return true;
	}
	
	protected void dojob(String material) {
		dojob();
		System.out.println("Building the " + material + " roof ");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Builder";
	}
	
	
	
	
	
	
}
