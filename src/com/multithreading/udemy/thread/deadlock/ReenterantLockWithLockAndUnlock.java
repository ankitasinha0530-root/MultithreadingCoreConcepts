package com.multithreading.udemy.thread.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterantLockWithLockAndUnlock {
	
	private static int counter = 0;
	private static Lock lock = new ReentrantLock();
	
	public static void increment() {
		try {
			lock.lock();
			counter++;
		}finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				ReenterantLockWithLockAndUnlock.increment();
			}
		});
		
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				ReenterantLockWithLockAndUnlock.increment();
			}
		});
		
		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finally counter = " + counter);
	}
}


