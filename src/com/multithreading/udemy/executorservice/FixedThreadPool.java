package com.multithreading.udemy.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> System.out.println(Thread.currentThread().getName() + " executing task");

        for (int i = 1; i <= 5; i++) {
            executor.submit(task); // works for both Runnable and callable interface
        }
        executor.shutdown();
    }
}
