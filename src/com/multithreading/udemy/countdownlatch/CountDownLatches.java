package com.multithreading.udemy.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is used to synchronize one or more tasks by forcing them to wait for the completion of a set of operations
 * being performed by other tasks
 *
 * 		- You give an initial count to a CountDownLatch object, and any task that calls await()
 * 				on that object will block until the count reaches zero
 *
 * 		- Other tasks may call countDown() on the object to reduce the count, presumably when a task finishes its job
 * 		- A CountDownLatch is designed to be used in a one-shot fashion; the count cannot be reset !!!
 * 				If you need a version that resets the count, you can use a CyclicBarrier instead
 *
 * 		- The tasks that call countDown() are not blocked when they make that call.
 * 		Only the call to await() is blocked until the count reaches zero
 *
 * 		A typical use is to divide a problem into n independently solvable tasks and create a CountDownLatch with a value of n.
 * 		When each task is finished it calls countDown() on the latch. Tasks waiting for the problem to be solved call await()
 * 		on the latch to hold themselves back until it is completed
 *
 */

class Worker implements Runnable {

	private int id;
	private CountDownLatch latch;
	private Random random;
	
	public Worker(int id, CountDownLatch latch) {
		this.latch = latch;
		this.id = id;
		this.random = new Random();
	}

	public void run() {
		doWork();
		latch.countDown(); // after completion of work call this to reduce the count of latch
	}

	public void doWork() {
		try {
			System.out.println("Thread with ID "+this.id+" starts working...");
			Thread.sleep(this.random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class CountDownLatches {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(5);

		for (int i = 0; i < 5; i++) // number of tasks shopuld always be greater than or equal to the latch count, else the code will freezes
			executorService.execute(new Worker(i,latch));
	
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All the prerequisites are done...");
		
		executorService.shutdown(); 
	}
}

/**
Very common in production systems:

		✅ Wait for multiple services to start
✅ Wait for parallel API calls
✅ Wait for multiple DB loads
✅ Microservice initialization
✅ Parallel test execution
✅ Kafka consumers readiness (relevant to your backend work)

 🔹 await() With Timeout (Important Variant)

 Sometimes you don't want to wait forever.

	 boolean completed = latch.await(5, java.util.concurrent.TimeUnit.SECONDS);

	 if (completed) {
	 System.out.println("All workers finished");
	 } else {
	 System.out.println("Timeout occurred");
 }

 ✔ await() → waits
 ✔ countDown() → reduces count
 ✔ Count reaches 0 → release all waiting threads
 ✔ Cannot reset (unlike CyclicBarrier)
 ✔ One-time use latch

 Workers wait until main thread signals start.

 CountDownLatch startLatch = new CountDownLatch(1);

	 Runnable worker = () -> {
		 try {
			 System.out.println(Thread.currentThread().getName() + " waiting");
			 startLatch.await(); // wait for signal
			 System.out.println(Thread.currentThread().getName() + " started");
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
	 };

	 new Thread(worker).start();
	 new Thread(worker).start();

	 Thread.sleep(2000);

	 System.out.println("Main thread releasing workers");
	 startLatch.countDown();

 */
