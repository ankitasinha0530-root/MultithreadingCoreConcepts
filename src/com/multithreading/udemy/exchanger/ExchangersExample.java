package com.multithreading.udemy.exchanger;

import java.util.concurrent.Exchanger;

/**
 * With the help of Exchanger -> two threads can exchange objects
 * 
 * exchange() -> exchanging objects is done via one of the two exchange()
 * methods
 * 
 * 	For example: genetic algorithms, training neural networks
 *
 */

class FirstWorkerExchangers implements Runnable {

	private int counter;
	private Exchanger<Integer> exchanger;

	public FirstWorkerExchangers(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while (true) {

			counter = counter + 1;
			System.out.println("FirstWorker incremented the counter: " + counter);
			
			try {
				counter = exchanger.exchange(counter);
				System.out.println("Exchenge done from 1 to 2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondWorkerExchangers implements Runnable {

	private int counter;
	private Exchanger<Integer> exchanger;

	public SecondWorkerExchangers(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while (true) {

			counter = counter - 1;
			System.out.println("SecondWorker decremented the counter: " + counter);
			
			try {
				counter = exchanger.exchange(counter);
				System.out.println("Exchenge done from two to one");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ExchangersExample {

	public static void main(String[] args) {

		Exchanger<Integer> exchanger = new Exchanger<>();

		new Thread(new FirstWorkerExchangers(exchanger)).start();
		new Thread(new SecondWorkerExchangers(exchanger)).start();

	}
}