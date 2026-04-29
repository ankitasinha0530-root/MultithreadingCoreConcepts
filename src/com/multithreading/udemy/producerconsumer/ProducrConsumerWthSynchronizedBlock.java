package com.multithreading.udemy.producerconsumer;

import java.util.ArrayList;
import java.util.List;


public class ProducrConsumerWthSynchronizedBlock {
	
	List<Integer> list = new ArrayList<>();
	public static final int UPPER_LIMIT = 5;
	public static final int LOWER_LIMIT = 0;
	
	public final Object lock = new Object();
	
	private int value = 0;
	
	public void producer() throws InterruptedException{
		
		synchronized(lock) {
			while(true) {
				if(list.size() == UPPER_LIMIT) {
					System.out.println("Waiting for Items to be removed");
					lock.wait();
				}else {
					System.out.println("Adding Value : " + value);
					list.add(value);
					value++;
					lock.notify();
				}
			}
		}
	}
	
	public void consumer() throws InterruptedException{
		
		synchronized(lock) {
			while(true) {
				if(list.size() == LOWER_LIMIT) {
					System.out.println(" initialize value to 0 and Waiting for items to be added");
					value = 0;
					lock.wait();
				}else {
					System.out.println("Removing Value : " + list.remove(list.size() - 1));
					lock.notify();
				}
			}
		}
	}

	public static void main(String[] args) {
		
		ProducrConsumerWthSynchronizedBlock process = new ProducrConsumerWthSynchronizedBlock();
		
		Thread t1 = new Thread(() -> {
			try {
				process.producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		
		Thread t2 = new Thread(() -> {
			try {
				process.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t2.start();

	}

}
