package com.multithreading.udemy.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockEaxmple {
	
	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);
	

	public static void main(String[] args) {
		
		DeadLockEaxmple deadlock = new DeadLockEaxmple();
		
		new Thread(deadlock::worker1, "worker1").start();
		new Thread(deadlock::worker2, "worker2").start();

	}
	
	public void worker1() {
		lock1.lock();
		System.out.println("worker1 acquired lock1");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lock2.lock();
		System.out.println("worker1 acquires lock2");
	
		lock1.unlock();
		System.out.println("worker1 releases lock1");
		lock2.unlock();
		System.out.println("worker1 releases lock2");
	}
	
//	since both workers are not acquiring the lock in same order, hence the deadlock
// In order to resolve the deadlock make sure that all the multiple threads acquire the lock in same order
	public void worker2() {
		// below 2 lines will create deadlock so first acquire lock 1 and then lock 2 in the same order as worker 1
		lock2.lock(); 
		System.out.println("worker2 acquired lock2");
		
//		lock1.lock();   //code  to resolve the deadlock issue
//		System.out.println("worker2 acquired lock1");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// instead of lock 1 acquire lock 2 
		lock1.lock();
		System.out.println("worker2 acquires lock1");
		
//		lock2.lock();
//		System.out.println("worker2 acquires lock2");
	
		lock1.unlock();
		lock2.unlock();
	}

}
