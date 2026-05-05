package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class CancellationExample {
    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    CompletableFutureUtils.sleep(5);
                    return "Done";
                });

        future.cancel(true);

        System.out.println("Cancelled: " + future.isCancelled());
    }
}
