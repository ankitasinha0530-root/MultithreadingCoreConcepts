package com.multithreading.udemy.deadlock;

public class DeadlockIssueResolve {
	
	private static final Object LOCK1 = new Object();
	private static final Object LOCK2 = new Object();

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runner1());
		Thread t2 = new Thread(new Runner2());
		
		t1.start();
		t2.start();
	}


	static class Runner1 implements Runnable{

		@Override
		public void run() {
			while(true) {
				synchronized(LOCK1) {
					System.out.println(Thread.currentThread().getName() + " acquired LOCK1");
				}
				try{
					Thread.sleep(100);
				}catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}
				System.out.println(Thread.currentThread().getName() + " Trying to acquire LOCK2");
				synchronized (LOCK2){
					System.out.println(Thread.currentThread().getName() + " acquired LOCK2");
				}
			}
		}
	}

	static class Runner2 implements Runnable{

		@Override
		public void run() {
			while(true) {
				synchronized(LOCK1) {
					System.out.println(Thread.currentThread().getName() + " acquired LOCK1");
				}
				try{
					Thread.sleep(2000);
				}catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}
				System.out.println(Thread.currentThread().getName() + " Trying to acquire LOCK2");
				synchronized (LOCK2){
					System.out.println(Thread.currentThread().getName() + " acquired LOCK2");
				}
			}
		}
	}

}


