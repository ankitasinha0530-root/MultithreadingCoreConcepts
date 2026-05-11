package com.multithreading.udemy.concurrentcollection.concurrenthashmap;

import java.util.concurrent.*;

public class ConcurrentHashMapExample {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for (int i = 1; i <= 5; i++) {
                map.put(i, Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map);
    }
}
