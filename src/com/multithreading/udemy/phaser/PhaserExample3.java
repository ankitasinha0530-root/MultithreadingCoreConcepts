package com.multithreading.udemy.phaser;

//Java program to demonstrate
//the methods of Phaser class

import java.util.concurrent.Phaser;

//Extend MyPhaser and override onAdvance()
//so that only specific number of phases
//are executed
class MyPhaser extends Phaser {
	int numPhases;

	MyPhaser(int parties, int phaseCount) {
		super(parties);
		numPhases = phaseCount - 1;
	}

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		System.out.println("Phase " + phase + " completed.\n");

		// If all phases have completed, return true.
		if (phase == numPhases || registeredParties == 0) {
			return true;
		}

		// otherwise, return false
		return false;
	}
}

//A thread of execution that uses a phaser
class ModifiedThread implements Runnable {
	Phaser phsr;
	String name;

	ModifiedThread(Phaser p, String n) {
		phsr = p;
		name = n;
		phsr.register();
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (!phsr.isTerminated()) {
			System.out.println("Thread " + name + " Beginning Phase " + phsr.getPhase());
			phsr.arriveAndAwaitAdvance();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}

public class PhaserExample3 {
	public static void main(String[] args) {
		MyPhaser phsr = new MyPhaser(1, 4);
		System.out.println("Starting");

		new ModifiedThread(phsr, "A");
		new ModifiedThread(phsr, "B");
		new ModifiedThread(phsr, "C");

		while (!phsr.isTerminated()) {
			phsr.arriveAndAwaitAdvance();
		}
		System.out.println("The phaser is terminated\n");
	}
}
