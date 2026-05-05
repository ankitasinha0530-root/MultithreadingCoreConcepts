package com.multithreading.udemy.CompletableFuture;

import java.util.concurrent.*;

public class ThenComposeDependentTask {
    public static void main(String[] args) throws Exception {

        CompletableFuture<String> result =
                CompletableFuture.supplyAsync(() -> "UserId")
                        .thenCompose(id ->
                                CompletableFuture.supplyAsync(() -> "Data for " + id)
                        );

        System.out.println(result.get());
    }
}
