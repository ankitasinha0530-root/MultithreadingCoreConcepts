package com.multithreading.udemy.executorservice.exceptionhandling;

import java.util.concurrent.*;

public class SubmitFutureExceptionExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> future = executor.submit(() -> {
            System.out.println("Task started");
            throw new RuntimeException("Exception in submit()");
        });

        try {
            future.get();
        }
        catch (ExecutionException e) {
            System.out.println(
                    "Caught exception: " + e.getCause()
            );
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}
