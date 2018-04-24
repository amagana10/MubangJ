package com.revature.hello;

public class Fruit {
	
	protected String color;
	
	public Fruit() {
		super();
	}
	
	public Fruit(String color) {
		this.color = color;
	}
	
	public void grow() {
		System.out.println("fruit is growing");	
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		
		return false;
	}
	
}
