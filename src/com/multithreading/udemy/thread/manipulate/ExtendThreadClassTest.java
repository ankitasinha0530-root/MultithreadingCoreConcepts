package com.multithreading.udemy.thread.manipulate;

public class ExtendThreadClassTest {

	public static void main(String[] args) {

		Thread t1 = new Runner3(); // Runner3 extends thread class
		Thread t2 = new Runner4(); // Runner4 extends thread class

		t1.start();
		t2.start();

	}
}

class Runner3 extends Thread{

	@Override
	public void run() {
		int i = 0;
		for(i =0; i< 10; i++) {

			System.out.println("Runner3 : " + i);
		}
	}
}

class Runner4 extends Thread{

	@Override
	public void run() {
		int i = 0;
		for(i = 0; i< 10; i++) {
			System.out.println("Runner4 : " + i);
		}
	}
}

