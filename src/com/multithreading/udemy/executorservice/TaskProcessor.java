package com.multithreading.udemy.executorservice;

import java.util.concurrent.*;

public class TaskProcessor {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                        2,
                        5,
                        60,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(10)
                );

        for (int i = 1; i <= 20; i++) {
            int taskId = i;
            executor.submit(() -> {System.out.println("Processing Task "
                                + taskId
                                + " by "
                                + Thread.currentThread().getName()
                );
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
            });
        }
        executor.shutdown();
    }
}
