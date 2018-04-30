package com.Revature.ProducerConsumer;

import java.util.Map;

public class Producer implements Runnable {
	
	private Map<String,Integer> Basket;
	
	public Producer(Map<String, Integer> basket) {
		super();
		Basket = basket;
	}
	
	
	public Map<String, Integer> getBasket() {
		return Basket;
	}

	@Override
	public void run() {
		
//		synchronized(this) {
			if (Basket.isEmpty()) {
				System.out.println("100 items added to the basket");
				Basket.put("item", 100);
//				notify();
				return;
				
			} else if(Basket.get("item") < 100) {
				System.out.println((100 - Basket.get("item")) + " items were added.");
				Basket.put("item", 100);
//				notify();
				return;
			}
			
//		}

				
	}
	
	

}
