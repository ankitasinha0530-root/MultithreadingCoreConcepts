package com.multithreading.udemy.thread.manipulate;

public class RunnableTest {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runner1());
		Thread t2 = new Thread(new Runner2());

		t1.start();
		t2.start();

	}
}

// Its better to implement Runnable
class Runner1 implements Runnable{

	@Override
	public void run() {
		int i = 0;
		for(i =0; i< 10; i++) {

			System.out.println("Runner1 : " + i);
		}
	}
}

class Runner2 implements Runnable{

	@Override
	public void run() {
		int i = 0;
		for(i = 0; i< 10; i++) {
			System.out.println("Runner2 : " + i);
		}
	}
}

