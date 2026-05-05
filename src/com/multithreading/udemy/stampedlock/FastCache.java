package com.multithreading.udemy.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class FastCache {

    private int data;
    private final StampedLock lock = new StampedLock();

    public int get() {
        long stamp = lock.tryOptimisticRead();
        int result = data;

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                result = data;
            } finally {
                lock.unlockRead(stamp);
            }
        }

        return result;
    }

    public void update(int value) {
        long stamp = lock.writeLock();
        try {
            data = value;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
