package com.multithreading.udemy.Reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class Counter {

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    public void increment() {

        lock.lock();   // acquire lock

        try {
            count++;
        } finally {
            lock.unlock(); // always release lock
        }
    }

    public int getCount() {
        return count;
    }
}

public class ReentrantLockBasicExample {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}
