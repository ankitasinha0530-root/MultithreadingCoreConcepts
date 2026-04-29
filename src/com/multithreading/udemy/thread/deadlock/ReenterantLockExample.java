package com.multithreading.udemy.thread.deadlock;

public class ReenterantLockExample {
	
	 public synchronized void outerMethod() {
	        System.out.println("Entered outerMethod");
	        innerMethod(); // Calling another synchronized method
	        System.out.println("Exiting outerMethod");
	    }
	 
	    public synchronized void innerMethod() {
	        System.out.println("Entered innerMethod");
	        // Do something
	        System.out.println("Exiting innerMethod");
	    }
	 
	    public static void main(String[] args) {
	    	ReenterantLockExample example = new ReenterantLockExample();
	 
	        Thread thread = new Thread(() -> {
	            example.outerMethod();
	        });
	 
	        thread.start();
	    }
	}

/**
 * outerMethod and innerMethod are both synchronized.
 * When a thread enters outerMethod, it acquires the object's intrinsic lock.
 * It can then safely enter innerMethod—even though it's also synchronized—because it already holds the lock. 
 * 
 * This is re-entrant synchronization.
 * 
 * A thread cannot acquire a lock that is owned by another thread. 
 * However, a thread can acquire a lock that it already owns. This is known as re-entrant synchronization.
 * Re-entrant synchronization allows a thread to acquire the same lock multiple times without causing a deadlock.
 * For example, consider a recursive method. If a thread calls a recursive, synchronized method multiple times, i
 * t's fine—the same thread re-enters the synchronized block each time. 
 * There’s no deadlock because re-entrant synchronization allows the thread to re-acquire the lock it already holds.
 */



