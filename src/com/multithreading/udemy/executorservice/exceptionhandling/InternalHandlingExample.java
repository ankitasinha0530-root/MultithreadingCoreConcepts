package com.multithreading.udemy.executorservice.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InternalHandlingExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                processData();
            }
            catch (Exception e) {
                System.out.println("Handled inside task: " + e);
            }
        });
        executor.shutdown();
    }

    static void processData() {
        throw new RuntimeException(
                "Failure inside business logic"
        );
    }
}
/**
Very common in :-

Kafka consumers
Batch jobs
Message processing systems*/
