package com.multithreading.udemy.CompletableFuture;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class AllOfAndCollectingResult {
    public static void main(String[] args) {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);

        all.join();

        List<Integer> results = Stream.of(f1, f2)
                .map(CompletableFuture::join)
                .toList();

        System.out.println(results);
    }
}
