package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class DeadlockExample {
    public static void main(String[] args) throws Exception {

        CompletableFuture<String> f =
                CompletableFuture.supplyAsync(() -> {
                    return CompletableFuture
                            .supplyAsync(() -> "Inner")
                            .join(); // dangerous
                });

        System.out.println(f.get());
    }
}
