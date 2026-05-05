package com.multithreading.udemy.virtualThreadExecutors;

import java.util.concurrent.Executors;

public class PinningExampleBadCode {

    static final Object lock = new Object();

    public static void main(String[] args) throws Exception {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    synchronized (lock) {
                        try {
                            Thread.sleep(1000); // BLOCKING inside synchronized
                        } catch (InterruptedException e) {}
                    }
                });
            }
        }
    }
}

/**
🚨 What happens?
    Virtual thread enters synchronized
    JVM CANNOT unmount
    Carrier thread is blocked

👉 You lose scalability
 */
