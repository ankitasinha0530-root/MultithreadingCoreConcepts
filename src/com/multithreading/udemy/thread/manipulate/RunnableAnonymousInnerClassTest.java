package com.multithreading.udemy.thread.manipulate;


public class RunnableAnonymousInnerClassTest {

	public static void main(String[] args) {
		
		var t1 = new Thread(() -> {
			for(int i = 0; i < 10; i++) {
				System.out.println("Runner1 : " + i);
			}
		});
		
		var t2 = new Thread(() -> {
			for(int i = 0; i< 10; i++) {
				System.out.println("Runner2 : " + i);
			}
		});
		
		t1.start();
		t2.start();
		
	}
}
