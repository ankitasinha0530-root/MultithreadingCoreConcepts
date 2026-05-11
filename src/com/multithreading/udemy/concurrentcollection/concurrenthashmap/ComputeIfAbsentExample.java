package com.multithreading.udemy.concurrentcollection.concurrenthashmap;

import java.util.concurrent.*;

public class ComputeIfAbsentExample {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.computeIfAbsent("count", k -> 1);

        System.out.println(map);
    }
}
