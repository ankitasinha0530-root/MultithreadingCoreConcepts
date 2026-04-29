package com.multithreading.udemy.thread.deadlock;


public class LockingClassLevelTest {
	
	public static void main(String[] args) {
		 
		 Runnable task1 = LockingClassLevel::instanceMethod;
		 Runnable task2 = LockingClassLevel::instanceMethod;
		 
		 new Thread(task1, "First Thread").start();
		 new Thread(task2, "Second Thread").start();
	}
	
}

/**
 * Since the method is static so we cant create diff object instead 
 * We will create the runnables based on LockingClassLevel class 
 * and When we run first the 1st thread is executed and wait for given task to execute
 * and then the second thread is executed and 
 * so the Lock is tied to the class object and shared across all instances
 * both threads calls the static synchronized method so they share same class level Lock
 * and Only one thread at a time can hold the lock and execute
 * 2nd thread will have to wait untill untill the first thread releases the lock
 * 
 * OUTPUT
 * First Thread Entered InstanceMethod
 * First Thread Finished InstanceMethod
 * Second Thread Entered InstanceMethod
 * Second Thread Finished InstanceMethod
 */

class LockingClassLevel {
	
	public static synchronized void instanceMethod() {
		
		System.out.println(Thread.currentThread().getName() + " Entered InstanceMethod");
		
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(Thread.currentThread().getName() + " Finished InstanceMethod");
		
	}
}




