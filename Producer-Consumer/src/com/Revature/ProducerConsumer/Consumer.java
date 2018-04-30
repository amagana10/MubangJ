package com.Revature.ProducerConsumer;

import java.util.Map;
import java.util.Random;

public class Consumer implements Runnable {
	
	private Map<String,Integer> basket;
	
	public Consumer(Map<String, Integer> basket) {
		super();
		this.basket = basket;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		int randNum = 0;
		
		while (basket.get("item") != 0) {
			
			randNum = rand.nextInt(100) + 1;
			while (randNum > basket.get("item")) {
				randNum = rand.nextInt(100) + 1;
			}
			
			System.out.println("The consumer took out: " + randNum + " items");
			
			basket.replace("item", basket.get("item"), (basket.get("item") - randNum));
			
			System.out.println("There are " + basket.get("item") + " items in the basket left.");
			System.out.println("---------------------------------");
		}

		
	}

}
