package com.multithreading.udemy.MapReduce;

import java.util.*;
import java.util.stream.Collectors;

public class MapReduceAdvanced {

    public static void main(String[] args) {

        List<String> data = List.of(
                "hello world",
                "hello java",
                "world of java"
        );

        Map<String, Integer> result =
                data.stream()
                        .flatMap(line -> Arrays.stream(line.split(" ")))
                        .collect(Collectors.toMap(
                                word -> word,
                                word -> 1,
                                Integer::sum   // merge function
                        ));

        System.out.println("Advanced MapReduce Output:");
        result.forEach((k, v) -> System.out.println(k + " → " + v));
    }
}
