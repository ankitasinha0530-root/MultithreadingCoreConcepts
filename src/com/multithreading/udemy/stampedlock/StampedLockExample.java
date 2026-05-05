package com.multithreading.udemy.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    private int value = 0;
    private final StampedLock lock = new StampedLock();

    public int read() {
        long stamp = lock.tryOptimisticRead();

        int current = value;

        // validate if no write happened
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                current = value;
            } finally {
                lock.unlockRead(stamp);
            }
        }

        return current;
    }

    public void write(int newValue) {
        long stamp = lock.writeLock();
        try {
            value = newValue;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
