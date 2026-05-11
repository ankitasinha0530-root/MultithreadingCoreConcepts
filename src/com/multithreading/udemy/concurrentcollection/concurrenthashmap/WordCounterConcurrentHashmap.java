package com.multithreading.udemy.concurrentcollection.concurrenthashmap;

import java.util.concurrent.*;

public class WordCounterConcurrentHashmap {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Runnable task = () -> {
            String word = "java";
            map.merge(word, 1, Integer::sum);
        };

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }
}
