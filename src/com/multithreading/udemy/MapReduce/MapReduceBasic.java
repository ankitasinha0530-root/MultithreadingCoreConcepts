package com.multithreading.udemy.MapReduce;

import java.util.*;

public class MapReduceBasic {

    public static void main(String[] args) {

        List<String> data = List.of(
                "hello world",
                "hello java",
                "world of java"
        );

        // MAP PHASE
        Map<String, List<Integer>> mapped = new HashMap<>();

        for (String line : data) {
            String[] words = line.split(" ");

            for (String word : words) {
                mapped.computeIfAbsent(word, k -> new ArrayList<>()).add(1);
            }
        }

        System.out.println("After MAP: " + mapped);

        // REDUCE PHASE
        Map<String, Integer> reduced = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : mapped.entrySet()) {
            int sum = 0;

            for (int val : entry.getValue()) {
                sum += val;
            }

            reduced.put(entry.getKey(), sum);
        }

        System.out.println("Final REDUCE Output:");
        reduced.forEach((k, v) -> System.out.println(k + " → " + v));
    }
}
