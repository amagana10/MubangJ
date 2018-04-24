package com.revature.hello;

public class Orange extends Fruit {
	
	private String variety;
	
	
	{
		System.out.println("this is an instance code block and will run when this class is instantiated.");
		// runs before constructor
	}
	
	// static
	static {
		System.out.println("this is an instance code block and will run when this class passes through the JVM");
	}
	
	public Orange() {
		super();
	}
	
	public Orange(String variety) {
		this();
		this.variety = variety;
	}
	
	
	public String getVariety() {
		return variety;
	}
	
	public void setVariety(String variety) {
		this.variety = variety;
	}
	

	
	
}
