package com.multithreading.udemy.callableandfuture;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args)
            throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> 5 + 10; // This is callable

        Future<Integer> result = executor.submit(task); // submit callable task, it will always result a future object

        System.out.println(result.get());

        executor.shutdown();
    }
}
