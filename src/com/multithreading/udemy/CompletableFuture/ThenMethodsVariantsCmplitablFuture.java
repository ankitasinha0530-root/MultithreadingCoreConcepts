package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class ThenMethodsVariantsCmplitablFuture {
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")           // returns Value
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("Done"));

        CompletableFutureUtils.sleep(2);
    }
}

/**
 CompletableFuture.supplyAsync(() -> "Hello")       // start the process with input "Hello"
        .thenApply(s -> s + " World")               // returns Value
        .thenAccept(System.out::println)            // Consumes Value
        .thenRun(() -> System.out.println("Done")); // No input needed


👉 Chain behavior:

Method  	Input	Output
thenApply	✔   	✔
thenAccept	✔   	❌
thenRun 	❌   	❌

 .thenApply()       // same thread (may block)
 .thenApplyAsync()  // different thread (common pool)

 Custom executor:

 ExecutorService executor = Executors.newFixedThreadPool(5);

 CompletableFuture.supplyAsync(() -> "Hi", executor)
 .thenApplyAsync(s -> s + " Ankita", executor);

 👉 Interview trap:

 Non-async may run in calling thread
 Async always uses thread pool
 */