package com.multithreading.udemy.executorservice.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExceptionExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            System.out.println("Task started");
            throw new RuntimeException("Exception in execute()");
        });

        executor.shutdown();
    }
}
