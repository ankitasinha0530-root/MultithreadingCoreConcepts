package com.multithreading.udemy.virtualThreadExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class PinningFixedExample {

// 👉 Now virtual thread can unmount safely
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }
                });
            }
        }
    }
}
