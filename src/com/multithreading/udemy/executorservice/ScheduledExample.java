package com.multithreading.udemy.executorservice;

import java.util.concurrent.*;

public class ScheduledExample {

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Running...");

        scheduler.scheduleAtFixedRate(
                task,
                0,
                2, // runs every 2 secs
                TimeUnit.SECONDS // Timeunit can be secs or millisecs or minutes and so on
        );
    }
}

// Health checks
// Retry jobs
// Monitoring tasks
