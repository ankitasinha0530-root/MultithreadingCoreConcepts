package com.multithreading.udemy.thread.deadlock;

public class LockingObjectLevelTest {
	
	public static void main(String[] args) {
		
		 var obj1 = new LockingObjectLevel();
		 var obj2 = new LockingObjectLevel();
		 
		 Runnable task1 = obj1::instanceMethod;
		 Runnable task2 = obj2::instanceMethod;
		 
		 new Thread(task1, "First Thread").start();
		 new Thread(task2, "Second Thread").start();
	}
}

/** In Object locking since we have two different Objects and no static synchronized methods 
 * hence Object level locking is used - 
 * Lock is attached to the Object 
 * 1st Thread can execute the Instance method of the 1st Object and 
 * 2nd Thread can execute the Instance method of the 2nd Object simultaneously
 * These are independent objects hence can be executed at the same time
 * 
 * OUTPUT
First Thread Entered InstanceMethod
Second Thread Entered InstanceMethod
First Thread Finished InstanceMethod
Second Thread Finished InstanceMethod
*/

class LockingObjectLevel {
	
	public synchronized void instanceMethod() {
		
		System.out.println(Thread.currentThread().getName() + " Entered InstanceMethod");
		
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(Thread.currentThread().getName() + " Finished InstanceMethod");
		
	}
}




