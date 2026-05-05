package com.multithreading.udemy.lockfreewaitfree;

import java.util.concurrent.atomic.AtomicIntegerArray;

class WaitFreeCounter {

    private AtomicIntegerArray counters;

    public WaitFreeCounter(int threads) {
        counters = new AtomicIntegerArray(threads);
    }

    public void increment(int threadId) {
        counters.incrementAndGet(threadId);
    }

    public int getTotal() {
        int sum = 0;
        for (int i = 0; i < counters.length(); i++) {
            sum += counters.get(i);
        }
        return sum;
    }
}
