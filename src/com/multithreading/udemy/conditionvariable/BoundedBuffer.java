package com.multithreading.udemy.conditionvariable;

import java.util.concurrent.locks.*;

public class BoundedBuffer {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int count = 0;
    private final int capacity = 5;

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while (count == capacity) {
                notFull.await();
            }

            count++;
            System.out.println("Produced → " + count);

            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }

            System.out.println("Consumed → " + count);
            count--;

            notFull.signal();

        } finally {
            lock.unlock();
        }
    }
}
