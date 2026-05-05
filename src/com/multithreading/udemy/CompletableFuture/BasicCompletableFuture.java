package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class BasicCompletableFuture {
    public static void main(String[] args) throws Exception {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    CompletableFutureUtils.sleep(2);
                    return "Hello";
                });

        System.out.println("Result: " + future.get());
    }
}
