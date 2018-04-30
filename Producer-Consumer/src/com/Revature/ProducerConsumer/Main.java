package com.Revature.ProducerConsumer;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		
		Map<String, Integer> basket = new HashMap<>();
		Map<String, Integer> basket2 = new HashMap<>();
		basket.put("item", 20);
		
		Producer producer = new Producer(basket);
		Consumer consumer = new Consumer(basket);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		
		try {
			producerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		consumerThread.start();
		
	}
}
