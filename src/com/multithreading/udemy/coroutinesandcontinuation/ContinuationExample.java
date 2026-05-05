package com.multithreading.udemy.coroutinesandcontinuation;

import java.util.concurrent.CompletableFuture;

public class ContinuationExample {

    public static void main(String[] args) {

        CompletableFuture<Void> flow =
                CompletableFuture.supplyAsync(() -> {
                    log("Step 1");
                    sleep(1000);
                    return "data";
                }).thenApply(data -> {
                    log("Step 2 with " + data);
                    sleep(1000);
                    return data + " processed";
                }).thenAccept(result -> {
                    log("Step 3 with " + result);
                });

        flow.join(); // wait
    }

    static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " → " + msg);
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}
/**
🧠 What’s happening?
Step1 → (continuation) → Step2 → (continuation) → Step3

👉 No blocking chain
👉 Flow is split into continuations*/
