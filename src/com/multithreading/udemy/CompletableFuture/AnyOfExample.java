package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class AnyOfExample {
    public static void main(String[] args) throws Exception {

        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    CompletableFutureUtils.sleep(3);
                    return "Slow";
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> "Fast");

        CompletableFuture<Object> result =
                CompletableFuture.anyOf(f1, f2);

        System.out.println(result.get());
    }
}
