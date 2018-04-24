package com.revature.building;

public class Main {
	public static void main(String[] args) {
		
		Worker inspector = new Inspector("Yes");
		Worker builder = new Builder(4);
		Room kitchen = new Room("kitchen", 80, 80);
		Room livingRoom = new Room("Living Room", 100, 100);
		Room mBedRoom = new Room("Master Bed Room", 120, 120);
		Room bedRoom = new Room("Bed Room", 100, 100);
		
		Building building = new Building(inspector, builder, kitchen, livingRoom, mBedRoom, bedRoom);
		
		building.isUnsurable();
		System.out.println(building.toString()); 
		
	}
}
