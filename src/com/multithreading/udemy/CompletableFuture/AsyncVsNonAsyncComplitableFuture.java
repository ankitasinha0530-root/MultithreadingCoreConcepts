package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class AsyncVsNonAsyncComplitableFuture {
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply(s -> {
            System.out.println("thenApply Thread: " + Thread.currentThread().getName());
            return s + " World";
        });

        CompletableFutureUtils.sleep(2);
    }
}
