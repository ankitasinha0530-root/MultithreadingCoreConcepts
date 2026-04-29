package com.multithreading.udemy.producerconsumer;

class Q {
	int num;
	boolean valueSet = false;

	public synchronized void put(int num) { // put method to be used by producer to increment and assign num
		while (valueSet) {
			try {
				wait();  // wait until the num value is read and printed
			} catch (Exception e) {
			} 
		}
		System.out.println("put : " + num);
		this.num = num;
		valueSet = true;
		notify();
	}

	public synchronized void get() { // get method to be used by consumer to get the produced number and print it
		while (!valueSet) {
			{
				try {
					wait(); // wait until the num value is created and assigned
				} catch (Exception e) {
				} 
			}
			System.out.println("get : " + num);
			valueSet = false;
			notify();
		}
	}
}

class producer implements Runnable { // create a producer class
	Q q; // get the reference of class which has actual method

	public producer(Q q) { // create a constructor with q as argument
		this.q = q; // get the object for q
		Thread prod = new Thread(this, "producer"); // create producer thread in constructor
		prod.start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true) { // loop will run forever
			q.put(i++); //// call the implemented method q to put and print the result
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}
}

class consumer implements Runnable { // create a consumer class
	Q q; // refer to object of q which has implementation of methods

	public consumer(Q q) { // create a constructor with q as argument
		this.q = q; // get the object for q
		Thread consume = new Thread(this, "consumer"); // create consumer thread in constructor
		consume.start();
	}

	@Override
	public void run() {
		while (true) { // loop will run forever
			q.get(); // call the implemented method q to get and print the result
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
		}
	}
}

public class producerConsumersyncchroMthdUsingWaitNotify {

	public static void main(String[] args) {

		Q q = new Q();

		new producer(q); // create producer and pass q object to invoke the constructor
		new consumer(q); // create producer and pass q object to invoke the constructor
	}

}
