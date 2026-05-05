package com.multithreading.udemy.coroutinesandcontinuation;

import java.util.concurrent.CompletableFuture;

public class ApiOrchestration {

    public static void main(String[] args) {

        CompletableFuture<String> result =
                callServiceA()
                        .thenCombine(callServiceB(), (a, b) -> {
                            return a + " + " + b;
                        })
                        .thenApply(combined -> {
                            return combined + " → final result";
                        });

        System.out.println(result.join());
    }

    static CompletableFuture<String> callServiceA() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "ServiceA";
        });
    }

    static CompletableFuture<String> callServiceB() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "ServiceB";
        });
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}
