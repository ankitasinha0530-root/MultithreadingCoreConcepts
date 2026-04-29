package com.multithreading.udemy.thread.manipulate;

class Runner5 implements Runnable{

	@Override
	public void run() {
		int i = 0;
		for(i =0; i< 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner1 : " + i);
		}
	}
}

class Runner6 implements Runnable{

	@Override
	public void run() {
		int i = 0;
		for(i = 0; i< 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner2 : " + i);
		}
	}
}


public class RunnableSleepMethodTest {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runner5());
		Thread t2 = new Thread(new Runner6());
		
		t1.start();
		t2.start();
		
	}
}
