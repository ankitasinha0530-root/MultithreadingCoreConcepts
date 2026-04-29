package com.multithreading.udemy.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Latch --> multiple threads can wait for each other
 *
 * A CyclicBarrier is used in situations where you want to create a group of
 * tasks to perform work in parallel + wait until they are all finished before
 * moving on to the next step -> something like join() -> something like
 * CountDownLatch
 * 
 * CountDownLatch: one-shot event CyclicBarrier: it can be reused over and over
 * again
 * 
 * + cyclicBarrier has a barrier action: a runnable, that will run automatically
 * when the count reaches 0 !!
 * 
 * new CyclicBarrier(N) -> N threads will wait for each other
 *
 * WE CAN NOT REUSE LATCHES BUT WE CAN REUSE CyclicBarriers --> reset() !!!
 * 
 */

class BarrierWorker2 implements Runnable {

	private final int id;
	private final Random random;
	private final CyclicBarrier cyclicBarrier;

	public BarrierWorker2(int id, CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
		this.random = new Random();
		this.id = id;
	}

	@Override
	public void run() {
		doWork();
	}

	private void doWork() {
		System.out.println("Thread with ID " + id + " starts the task...");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Thread with ID " + id + " finished...");

		try {
			cyclicBarrier.await();
			System.out.println("After await...");// conitnue after no of barrier threads are executed
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public String toString() { return ""+this.id; };
}

public class CyclicBarriersExample2 {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("We are able to use the trained neural network..."));
		
		for(int i=0;i<5;++i)
			executorService.execute(new BarrierWorker(i+1, barrier));
		
		executorService.shutdown();
		
		
		
		// 2nd example
		
		CyclicBarrier barriers = new CyclicBarrier(5, new Runnable() {
															@Override
															public void run() {
																	System.out.println("We are able to use the trained neural network...");
																}});
		
		for(int i=0;i<5;++i)
			executorService.execute(new BarrierWorker(i+1, barriers));
		
		executorService.shutdown();
	}

}

