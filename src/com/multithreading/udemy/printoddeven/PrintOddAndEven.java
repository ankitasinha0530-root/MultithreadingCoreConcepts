package com.multithreading.udemy.printoddeven;

public class PrintOddAndEven {
	int counter = 1; // Starting counter

	static int N;

	public void printOddNumber() { // Function to print odd numbers
		synchronized (this) {
			while (counter < N) { // Print number till the N
				while (counter % 2 == 0) { // If count is even then wait
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(counter + " "); // Print the number				
				counter++; // Increment counter
				notify();  // Notify to second thread
			}
		}
	}

	public void printEvenNumber() { // Function to print even numbers
		synchronized (this) {
			// Print number till the N
			while (counter < N) {
				while (counter % 2 == 1) { // If count is odd then print
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(counter + " "); // Print the number
				counter++; // Increment counter
				notify(); // Notify to 2nd thread
			}
		}
	}

	// Driver Code
	public static void main(String[] args) {
		
		N = 10;  // Given Number N
		PrintOddAndEven printOddEven = new PrintOddAndEven();// Create an object of class

		// Create thread t1
		Thread t1 = new Thread(() -> printOddEven.printEvenNumber());

		// Create thread t2
		Thread t2 = new Thread(() -> printOddEven.printOddNumber());

		// Start both threads
		t1.start();
		t2.start();
	}
}