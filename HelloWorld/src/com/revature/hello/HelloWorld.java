package com.revature.hello;

public class HelloWorld {
	
	public static void main(String[] args) {
		System.out.println("hello world!");
		
		Orange o = new Orange();
		
		o.setColor("Purple");
		
		System.out.println("Orange o is " + o.getColor());
		
		o.setVariety("Valencia");
		
		System.out.println("Orange o has variety: " + o.getVariety());
		
		System.out.println(o.toString()); // prints full qualifiedname + address
		
		Fruit f1 = new Fruit("red");
		System.out.println(f1.hashCode());
	}

}
