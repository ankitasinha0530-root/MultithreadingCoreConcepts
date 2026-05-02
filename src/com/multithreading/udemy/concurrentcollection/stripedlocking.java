package com.multithreading.udemy.concurrentcollection;

import java.util.concurrent.locks.*;

class StripedCounter {

    private int[] counters = new int[4];

    private ReentrantLock[] locks = new ReentrantLock[4];

    public StripedCounter() {
        for (int i = 0; i < 4; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    public void increment(int index) {
        locks[index].lock();
        try {
            counters[index]++;
        } finally {
            locks[index].unlock();
        }
    }
}
