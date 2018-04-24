package com.revature.building;

public class Room {
	
	String type;
	int length;
	int width;
	
	Room(String type, int length, int width) {
		this.type = type;
		this.length = length;
		this.width = width;
	}

	public String getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}	
	
	public int getDimension() {
		return length * width;
	}
}
