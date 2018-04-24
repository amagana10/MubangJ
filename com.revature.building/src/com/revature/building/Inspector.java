package com.revature.building;

public class Inspector extends Worker {
	String passInspection;
	
	Inspector(String passInspection) {
		this.passInspection = passInspection;
	}
	@Override
	boolean dojob() {
		if (this.passInspection == "Yes") {
			return true;
		} else {
			return false;
		}
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Inspector";
	}
	
	
}
