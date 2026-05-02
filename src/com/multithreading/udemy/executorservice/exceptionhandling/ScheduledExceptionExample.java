package com.multithreading.udemy.executorservice.exceptionhandling;

import java.util.concurrent.*;

public class ScheduledExceptionExample {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("Running task");
                throw new RuntimeException("Failure in scheduled task");
            }
            catch (Exception e) {
                System.out.println("Handled scheduled error: " + e);
            }
        }, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(6000);

        scheduler.shutdown();
    }
}
