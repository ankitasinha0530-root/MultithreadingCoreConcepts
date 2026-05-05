package com.multithreading.udemy.virtualThreadExecutors;

import java.util.*;
import java.util.concurrent.*;

public class VirtualThreadErrorHandling {

    public static void main(String[] args) throws Exception {

        List<Future<?>> futures = new ArrayList<>();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 10; i++) {
                int taskId = i;

                futures.add(executor.submit(() -> {
                    if (taskId % 3 == 0) {
                        throw new RuntimeException("Error in task " + taskId);
                    }
                    return "Task " + taskId + " success";
                }));
            }

            for (Future<?> f : futures) {
                try {
                    System.out.println(f.get());
                } catch (ExecutionException e) {
                    System.err.println("Handled error: " + e.getCause());
                }
            }
        }
    }
}
