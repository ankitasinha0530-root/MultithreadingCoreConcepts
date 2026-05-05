package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class RetryExample {

    public static CompletableFuture<String> retry(Supplier<String> task, int retries) {
        return CompletableFuture.supplyAsync(task)
                .handle((res, ex) -> {
                    if (ex == null) return CompletableFuture.completedFuture(res);
                    if (retries > 0) return retry(task, retries - 1);
                    return CompletableFuture.<String>failedFuture(ex);
                })
                .thenCompose(f -> f);
    }

    public static void main(String[] args) {
        retry(() -> {
            throw new RuntimeException("Fail");
        }, 3).exceptionally(ex -> "Final Failure")
                .thenAccept(System.out::println);

        CompletableFutureUtils.sleep(2);
    }
}
