package com.multithreading.udemy.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerWorker implements Runnable{

	BlockingQueue<Integer> bQueue;
	
	public ProducerWorker(BlockingQueue<Integer> bQueue) {
		this.bQueue = bQueue;
	}

	@Override
	public void run() {
		int count = 1;
		while(true) {
			try {
				bQueue.put(count);
				System.out.println("adding elements in Queue : " + count);
				count++;
				Thread.sleep(100);
				if(bQueue.size() == 10) {
					System.out.println("waiting for elements to be removed");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ConsumerWorker implements Runnable{
	
	BlockingQueue<Integer> bQueue;
	
	public ConsumerWorker(BlockingQueue<Integer> bQueue) {
		this.bQueue = bQueue;
	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("getting elements from Queue : " + bQueue.take());
				Thread.sleep(1000);
				if(bQueue.size() == 10) {
					System.out.println("waiting for elements to be removed");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ProdConsumeBlockingQueue {

	public static void main(String[] args) {

		BlockingQueue<Integer> bQueue = new ArrayBlockingQueue<>(10);
		
		ProducerWorker producer = new ProducerWorker(bQueue);
		ConsumerWorker consumer = new ConsumerWorker(bQueue);
		
		new Thread(producer).start();
		new Thread(consumer).start();

	}

}
