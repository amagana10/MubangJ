package com.Revature.ProducerConsumer;

import java.util.Map;
import java.util.Random;

public class Consumer implements Runnable {
	
	private Map<String,Integer> Basket;
	
	public Consumer(Map<String, Integer> basket) {
		super();
		Basket = basket;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		int randNum = rand.nextInt(100) + 1;
		
		while (randNum > Basket.get("item")) {
			randNum = rand.nextInt(100) + 1;
		}
		
		System.out.println("The consumer took out: " + (Basket.get("item") - randNum) + " items");
		
		Basket.replace("item", Basket.get("item"), Basket.get("item") - randNum);
		
	}

}
