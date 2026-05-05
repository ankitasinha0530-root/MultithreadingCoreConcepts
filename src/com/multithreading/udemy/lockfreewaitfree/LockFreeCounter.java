package com.multithreading.udemy.lockfreewaitfree;

import java.util.concurrent.atomic.AtomicInteger;

class LockFreeCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int oldValue;
        int newValue;

        do {
            oldValue = counter.get();
            newValue = oldValue + 1;
        } while (!counter.compareAndSet(oldValue, newValue));
    }

    public int get() {
        return counter.get();
    }
}
