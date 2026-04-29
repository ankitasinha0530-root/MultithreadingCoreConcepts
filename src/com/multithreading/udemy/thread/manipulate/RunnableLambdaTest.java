package com.multithreading.udemy.thread.manipulate;

// Pass runnable to the Thread constructor
public class RunnableLambdaTest {

	public static void main(String[] args) {
		
		Runnable r1 = () -> {
			for(int i = 0; i< 10; i++) {
				System.out.println("Runner1 : " + i);
			}
		};
		
		Runnable r2 = () -> {
			for(int i = 0; i< 10; i++) {
				System.out.println("Runner2 : " + i);
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
	}
}
