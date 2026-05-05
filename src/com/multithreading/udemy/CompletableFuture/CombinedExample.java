package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class CombinedExample {

    static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static String getUser() {
        CompletableFutureUtils.sleep(2);
        return "User";
    }

    public static String getOrders() {
        CompletableFutureUtils.sleep(3);
        return "Orders";
    }

    public static String getPayment(String user) {
        CompletableFutureUtils.sleep(1);
        return "Payment for " + user;
    }

    public static void main(String[] args) {

        CompletableFuture<String> userFuture =
                CompletableFuture.supplyAsync(CombinedExample::getUser, executor);

        CompletableFuture<String> ordersFuture =
                CompletableFuture.supplyAsync(CombinedExample::getOrders, executor);

        // dependent task
        CompletableFuture<String> paymentFuture =
                userFuture.thenCompose(user ->
                        CompletableFuture.supplyAsync(() -> getPayment(user), executor)
                );

        // combine results
        CompletableFuture<String> finalResult =
                userFuture.thenCombine(ordersFuture, (u, o) -> u + " | " + o)
                        .thenCombine(paymentFuture, (res, pay) -> res + " | " + pay)
                        .completeOnTimeout("Timeout Fallback", 5, TimeUnit.SECONDS)
                        .exceptionally(ex -> "Error: " + ex.getMessage());

        System.out.println(finalResult.join());

        executor.shutdown();
    }
}
