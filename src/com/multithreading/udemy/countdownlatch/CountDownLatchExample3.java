package com.multithreading.udemy.countdownlatch;

import java.util.concurrent.CountDownLatch;

class Worker3 implements Runnable {

    private CountDownLatch latch;
    private String name;

    public Worker3(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " started work");
            // Simulate work
            Thread.sleep(2000);
            System.out.println(name + " finished work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Reduce latch count
            latch.countDown();
            System.out.println(name + " called countDown()");
        }
    }
}

public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {

        int numberOfWorkers = 3;

        // Latch initialized with count 3
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        // Start worker threads
        Thread t1 = new Thread(new Worker3(latch, "Worker-1"));
        Thread t2 = new Thread(new Worker3(latch, "Worker-2"));
        Thread t3 = new Thread(new Worker3(latch, "Worker-3"));

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Main thread waiting...");

        // Main thread waits until count becomes 0
        latch.await();

        System.out.println("All workers finished. Main thread continues.");
    }
}
