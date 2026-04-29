package com.multithreading.udemy.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker1 implements Runnable{

	private int id;
	CountDownLatch latch;
	
	public Worker1(int id, CountDownLatch latch) {
		this.id = id;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		doWork();
		latch.countDown();  //this will reduce/decrement the countdown by 1
	}

	private void doWork() {
		try {
			System.out.println("Thread with id : " + id + " starts working...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class LatchCountDownExample {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(5);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
//		for (int i = 0; i < 5; i++) {
//			executor.execute(new Worker(i, latch));
//		}
		
		for (int i = 0; i < 8; i++) {
			executor.execute(new Worker(i, latch));
		}
			// if the number of task is less than the countdown value then countdown never becomes zero hence the code freezes
			// its crucial  that the number of tasks is equal to or greater than the countdown value to proceed with execution of next steps in the main thread
		try {	
		latch.await();  // the Java app will wait here untill the countdown is zero and then will execute the next steps in main thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All threads have completed execution ");
		System.out.println("I can execute further steps ");
		executor.shutdown();
	}

}
