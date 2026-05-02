package com.multithreading.udemy.executorservice.exceptionhandling;

import java.util.concurrent.*;

public class CustomThreadFactoryExample {

    public static void main(String[] args) {
        ThreadFactory factory = r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(
                    (thread, ex) -> {System.out.println("Global Handler caught: " + ex);
                    });
            return t;
        };

        ExecutorService executor = Executors.newFixedThreadPool(2, factory);
        executor.execute(() -> {
            throw new RuntimeException("Exception in thread");
        });

        executor.shutdown();
    }
}
