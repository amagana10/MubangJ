package com.Revature.ProducerConsumer;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		
		Map<String, Integer> basket = new HashMap<>();
		
		Producer producer = new Producer(basket);
		Consumer consumer = new Consumer(basket);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
		
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
//		synchronized(producerThread) {
//			try {
//				System.out.println("producer is filling basket");
//				producerThread.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		

	}
}
