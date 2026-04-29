package com.multithreading.udemy.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BarrierWorker implements Runnable{
	int id;
	CyclicBarrier barrier;
	Random random;
	
	public BarrierWorker(int id, CyclicBarrier barrier) {
		this.id = id;
		this.barrier = barrier;
		this.random = new Random();
	}

	// All the threads will wait for all the other threads to wait for the execution
	// its a mixture of join and countdown latch

	@Override
	public void run() {
		doWork();
	}
	private void doWork() {
		System.out.println("This thread : " + id + " starts executing the work...");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			// when all the threads call await() method, then the barrier is broken
			// else all the thread will be waiting at await()
			barrier.await();
			System.out.println("code after await");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println("After Await is done....");
	}
	
}

public class CyclicBarrierExample {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(5);

		// after the completion of all 5 threads the barrier action will get executed which takes impl of runnable class
		
		CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("All the threads completed execution"));
		
		CyclicBarrier barrier2 = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("All the threads completed execution");
			}});
		for (int i = 0; i < 5; i++) {
			executor.execute(new BarrierWorker(i+1, barrier));
		}
	}

}
