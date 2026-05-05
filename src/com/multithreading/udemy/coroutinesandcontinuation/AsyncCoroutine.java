package com.multithreading.udemy.coroutinesandcontinuation;

import java.util.concurrent.*;
// Coroutine + Async (Closer to real systems)
//👉 Combine state machine + async execution
public class AsyncCoroutine {

    static ExecutorService executor = Executors.newSingleThreadExecutor();

    static class Coroutine {

        int state = 0;

        CompletableFuture<Void> resume() {

            return CompletableFuture.runAsync(() -> {
                switch (state) {
                    case 0:
                        log("Step 1");
                        state = 1;
                        break;

                    case 1:
                        log("Step 2");
                        state = 2;
                        break;

                    case 2:
                        log("Step 3");
                        state = 3;
                        break;
                }
            }, executor);
        }

        boolean isDone() {
            return state == 3;
        }
    }

    public static void main(String[] args) {

        Coroutine c = new Coroutine();

        while (!c.isDone()) {
            c.resume().join(); // simulate scheduling
        }

        executor.shutdown();
    }

    static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " → " + msg);
    }
}
