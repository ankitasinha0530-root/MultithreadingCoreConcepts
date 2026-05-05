package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class TimeoutExample {
    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                            CompletableFutureUtils.sleep(5);
                            return "Done";
                        })
                        .completeOnTimeout("Default", 2, TimeUnit.SECONDS);

        System.out.println(future.join());
    }
}
