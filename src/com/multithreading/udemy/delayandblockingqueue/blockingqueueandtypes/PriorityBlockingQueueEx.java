package com.multithreading.udemy.delayandblockingqueue.blockingqueueandtypes;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueEx {

	public static void main(String[] args) {
		BlockingQueue<String> bQueue = new PriorityBlockingQueue<>();
		
		PriorityProducerWorker producer = new PriorityProducerWorker(bQueue);
		PriorityProducerWorker consumer = new PriorityProducerWorker(bQueue);
		
		new Thread(producer).start();
		new Thread(consumer).start();

	}
}

class PriorityProducerWorker implements Runnable{

	BlockingQueue<String> bQueue;
	public PriorityProducerWorker(BlockingQueue<String> bQueue) {
		this.bQueue = bQueue;
	}

	@Override
	public void run() {
		while(true) {
			try {
				bQueue.put("H");
				bQueue.put("L");
				Thread.sleep(100);
				bQueue.put("K");
				Thread.sleep(300);
				bQueue.put("A");
				Thread.sleep(200);
				bQueue.put("G");
				Thread.sleep(10);
				bQueue.put("F");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class PriorityConsumerWorker implements Runnable{

	BlockingQueue<String> bQueue;
	public PriorityConsumerWorker(BlockingQueue<String> bQueue) {
		this.bQueue = bQueue;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				System.out.println(bQueue.take());
				System.out.println(bQueue.take());
				Thread.sleep(300);
				System.out.println(bQueue.take());
				Thread.sleep(100);
				System.out.println(bQueue.take());
				System.out.println(bQueue.take());
				System.out.println(bQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

