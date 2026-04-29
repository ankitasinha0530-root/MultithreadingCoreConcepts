package com.multithreading.udemy.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureSingleThrdPoolExample2 {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> 5 + 10;
        Future<Integer> result = executor.submit(task);

        System.out.println(result.get());
        executor.shutdown();
//        Stops accepting new tasks
//        Completes existing tasks

//        executor.shutdownNow();
// Stops immediately
// Attempts to interrupt tasks
    }
}
