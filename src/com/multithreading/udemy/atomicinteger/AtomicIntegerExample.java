package com.multithreading.udemy.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {

    AtomicInteger count = new AtomicInteger(0);

    void increment() {
        count.incrementAndGet();
    }

    int getCount() {
        return count.get();
    }
}

public class AtomicIntegerExample {

    public static void main(String[] args) throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount());
    }
}

/**
// Increment
count.incrementAndGet();   // 11

// Decrement
count.decrementAndGet();   // 10

// Add value
count.addAndGet(5);        // 15

// Get value
count.get();

// Set value
count.set(20);

// Compare and swap
count.compareAndSet(20, 50);*/
