package com.Revature.ProducerConsumer;

import java.util.Map;

public class Producer implements Runnable {
	
	private Map<String,Integer> basket;
	
	public Producer(Map<String, Integer> basket) {
		super();
		this.basket = basket;
	}
	
	
	public Map<String, Integer> getBasket() {
		return basket;
	}
	
	public void add() {
		System.out.println((100 - basket.get("item")) + " items were added.");
		basket.put("item", 100);
	}
	

	@Override
	public void run() {
		
			if (basket.isEmpty()) {
				System.out.println("100 items added to the basket");
				basket.put("item", 100);
				return;
				
			} else if(basket.get("item") < 100) {
				System.out.println((100 - basket.get("item")) + " items were added.");
				basket.put("item", 100);
				return;
			}
		
				
	}
	

	

}
