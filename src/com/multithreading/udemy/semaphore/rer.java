package com.multithreading.udemy.semaphore;

import java.util.concurrent.Semaphore;

class SemaphoreExample {

    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {

        Runnable runnableTask = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " accessing resource");
                Thread.sleep(2000);
            } catch (Exception e) {}
            finally {
                semaphore.release();
            }
        };

        for (int i = 1; i <= 6; i++) {
            new Thread(runnableTask).start();
        }
    }
}