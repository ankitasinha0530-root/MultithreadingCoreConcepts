package com.multithreading.udemy.delayandblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

	public static void main(String[] args) {
		BlockingQueue<DelayedWorker> bQueue = new DelayQueue<>();
		
		try {
			// These can be inserted using multiple threads
			bQueue.put(new DelayedWorker(1000, "This is first Message"));
			bQueue.put(new DelayedWorker(10000, "This is second Message"));
			bQueue.put(new DelayedWorker(8000, "This is third Message"));
			bQueue.put(new DelayedWorker(4000, "This is fourth Message"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(!bQueue.isEmpty()) {
			try {
				System.out.println(bQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
	
	class DelayedWorker implements Delayed{
		
		long delay;
		String message; 
		
		public DelayedWorker(long delay, String message) {
			this.delay = System.currentTimeMillis() + delay;
			this.message = message;
		}

		@Override
		public int compareTo(Delayed other) {
			if(delay < ((DelayedWorker) other).getDelay())
				return -1;
			if(delay > ((DelayedWorker) other).getDelay())
				return 1;
			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			unit.convert(delay, TimeUnit.MILLISECONDS);
			return 0;
		}

		public long getDelay() {
			return delay;
		}

		public void setDelay(long delay) {
			this.delay = delay;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "DelayedWorker [delay=" + delay + ", message=" + message + "]";
		}
		
		
	}


