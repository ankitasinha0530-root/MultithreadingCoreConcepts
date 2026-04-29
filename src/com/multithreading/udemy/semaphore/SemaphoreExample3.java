package com.multithreading.udemy.semaphore;

import java.util.concurrent.Semaphore;

class SemaphoreWorker implements Runnable {

    private Semaphore semaphore;

    public SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " acquired permit");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing permit");
            semaphore.release();
        }
    }
}

public class SemaphoreExample3 {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 5; i++) {
            new Thread(new SemaphoreWorker(semaphore)).start();
        }
    }
}

/**
Permit limit = 2

Only 2 threads run simultaneously
Others wait

Used in:

DB connection pools
Rate limiting
Resource control
 */
