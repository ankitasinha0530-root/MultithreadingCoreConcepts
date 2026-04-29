package com.multithreading.udemy.countdownlatch;

import java.util.concurrent.*;

public class LatchExecutorExample {

    public static void main(String[] args) throws InterruptedException {

        int tasks = 3;
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(tasks);

        for (int i = 1; i <= tasks; i++) {
            int taskId = i;
            executor.submit(() -> {
                try {
                    System.out.println("Task " + taskId + " running");
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                    System.out.println("Task " + taskId + " finished");
                }
            });
        }

        latch.await();
        System.out.println("All tasks completed");
        executor.shutdown();
    }
}
