package com.multithreading.udemy.thread.deadlock;

import java.util.LinkedList;
import java.util.List;

class SharedBuffer{
	
	List<Integer> buffer = new LinkedList<>();
	int capacity = 8;
	
	public synchronized void produce() throws InterruptedException{
		
		while(buffer.size() == capacity) {
			System.out.println("Buffer is full, Producer is waiting");
			wait();
		}
		System.out.println("Adding Items with Producer");
		for (int i = 0; i < capacity; i++) {
			buffer.add(i);
			System.out.println("Added value to buffer : " + i);
			
		}
		// notify consumer
		notify();
		
	}
	
	public synchronized void consume() throws InterruptedException {
			
			while(buffer.size() < capacity) {
				System.out.println("Buffer is not full, Consumer is waiting");
				wait();
			}
			System.out.println("Consumer is removing Items");
			while(!buffer.isEmpty()) {
				int item = buffer.remove(0);
				System.out.println("removed Item from Buffer " + item);
				Thread.sleep(300);
			}
			// notify producer
			notify();
		}
}

class Producer implements Runnable{
	
	private SharedBuffer sharedBuffer;
	
	public Producer(SharedBuffer sharedBuffer) {
		this.sharedBuffer = sharedBuffer;
	}

	@Override
	public void run() {
		
		try {
			while(true) {
				this.sharedBuffer.produce();
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class Consumer implements Runnable{
	
	private SharedBuffer sharedBuffer;
	
	public Consumer(SharedBuffer sharedBuffer) {
		this.sharedBuffer = sharedBuffer;
	}
	

	@Override
	public void run() {
		try {
			while(true) {
				this.sharedBuffer.consume();	
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

public class producerConsumersyncchroMthdUsingWaitNotify {

	public static void main(String[] args) {

		var sh = new SharedBuffer();
		
		Thread t1 = new Thread(new Producer(sh));
		Thread t2 = new Thread(new Consumer(sh));
		
		t1.start();
		t2.start();
	}

}
