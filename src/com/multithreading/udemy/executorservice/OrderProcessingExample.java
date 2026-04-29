package com.multithreading.udemy.executorservice;

import java.util.concurrent.*;

public class OrderProcessingExample {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int orderId = i;
            executor.submit(() -> {
                System.out.println("Processing order " + orderId + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            });
        }
        executor.shutdown();
    }
}
