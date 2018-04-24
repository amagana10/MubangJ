package com.revature.building;

public class Building implements Insurable{
	private Worker inspector;
	private Worker Builder;
	private Room kitchen;
	private Room livingRoom;
	private Room MBedRoom;
	private Room BedRoom;
	
	
	
	public Building(Worker inspector, Worker builder, Room kitchen, Room livingRoom, Room mBedRoom, Room bedRoom) {
		super();
		this.inspector = inspector;
		Builder = builder;
		this.kitchen = kitchen;
		this.livingRoom = livingRoom;
		MBedRoom = mBedRoom;
		BedRoom = bedRoom;
	}
	
	


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Workers: " + inspector.toString() + ", " + Builder.toString() + " | " + "Rooms: " + kitchen.getType() + ", " + livingRoom.getType() + ", " + MBedRoom.getType() + ", " + BedRoom.getType();
	}




	@Override
	public boolean isUnsurable() {
		if (inspector.dojob() == true) {
			System.out.println("Passes Inspection");
			return true;
		} else {
			System.out.println("Does Not pass Inspection");
			return false;
		}
	}
	
	
}
