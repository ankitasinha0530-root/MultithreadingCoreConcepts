package com.multithreading.udemy.livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockResolveExample {

	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);

	public static void main(String[] args) {

		LiveLockResolveExample liveLock = new LiveLockResolveExample();
//		System.out.println("Start................");
		new Thread(liveLock::worker1, "worker1").start();
// 		new Thread(liveLock::worker2, "worker2").start();
		new Thread(liveLock::worker2IssueReolveMethod, "worker2").start();
	}

	public void worker1() {

		while (true) {
			try {
				lock1.tryLock(50, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("worker1 has acquired lock1");
			System.out.println("worker1 tries to acquire lock2");
			if (lock2.tryLock()) {
				System.out.println("worker1 acquires lock2");
				lock2.unlock();
			} else {
				System.out.println("worker1 not able to acquire lock2");
				continue;
			}
			break;
		}
		lock1.unlock();
	}

	public void worker2IssueReolveMethod() {

		while (true) {
			try {
				lock1.tryLock(50, TimeUnit.MILLISECONDS);
				System.out.println("worker2 has acquired lock1");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("worker2 tries to acquire lock2");
			if (lock2.tryLock()) {
				System.out.println("worker2 acquires lock2");
				lock2.unlock();
			} else {
				System.out.println("worker2 not able to acquire lock2");
				continue;
			}
			break;
		}
		lock1.unlock();
	}

	public void worker2() {

		while (true) {
			try {
				lock2.tryLock(50, TimeUnit.MILLISECONDS);
				System.out.println("worker2 has acquired lock2");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("worker2 tries to acquire lock1");
			if (lock1.tryLock()) {
				System.out.println("worker2 acquires lock1");
				lock1.unlock();
			} else {
				System.out.println("worker2 not able to acquire lock1");
				continue;
			}
			break;
		}
		lock1.unlock();
		lock2.unlock();
	}

}
