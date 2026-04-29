package com.multithreading.udemy.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithReEnterantLock {
	
	private Lock lock = new ReentrantLock();   // define Reenterant Lock
	private Condition condition = lock.newCondition();   // add a new Condition, this will help to call wait and notify equivalent methods
	
	public void producer() throws InterruptedException{
		
		lock.lock();   // make the critical piece of code (Critical block) thread safe
		try {   
			System.out.println("Producer Executing....");
			condition.await();      // Await for other thread to complete job
			System.out.println("Producer Executing Again....");
		}finally {
			lock.unlock();  // Unlock the lock which was retrieved, this step is must, can be in finally block irrespective of any issue occured
		}
	}
	
	public void consumer() throws InterruptedException{
		
		try {Thread.sleep(2000);}catch(Exception e){}
		lock.lock();        // acquire the reentrant lock
		try {
			System.out.println("Consumer Executing....");
			condition.signal();    // give signal to other threads that the lock that the execution is complete and lock is released
			try {Thread.sleep(3000);}catch(Exception e){}
		}finally {
			lock.unlock();// Unlock the lock which was retrieved, this step is must, can be in finally block irrespective of any issue occured
		}
	}

	public static void main(String[] args) {
		
		ProducerConsumerWithReEnterantLock worker = new ProducerConsumerWithReEnterantLock();
		
		Thread t1 = new Thread(() -> {
			try {
				worker.producer();
			}catch(Exception e){}
		});
		t1.start();
		
		Thread t2 = new Thread(() -> {
			try {
				worker.consumer();
			}catch(Exception e){}
		});
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("In Main now, All threads Completed Execution");
	}

}
