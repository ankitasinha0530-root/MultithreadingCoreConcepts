package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class ExceptionHandling {

    public static void main(String[] args) {

        // exceptionally
        CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Error");
                }).exceptionally(ex -> "Fallback")
                .thenAccept(System.out::println);

        // handle
        CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Boom");
                }).handle((res, ex) -> ex != null ? "Recovered" : res)
                .thenAccept(System.out::println);

        // whenComplete
        CompletableFuture.supplyAsync(() -> "Hello")
                .whenComplete((res, ex) -> System.out.println("Logged: " + res));

        CompletableFutureUtils.sleep(2);
    }
}
