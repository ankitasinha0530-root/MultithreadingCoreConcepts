package com.multithreading.udemy.MapReduce;

import java.util.*;
import java.util.stream.Collectors;

public class MapReduceStream {

    public static void main(String[] args) {

        List<String> data = List.of(
                "hello world",
                "hello java",
                "world of java"
        );

        Map<String, Long> result =
                data.stream()
                        .flatMap(line -> Arrays.stream(line.split(" ")))
                        .collect(Collectors.groupingBy(
                                word -> word,
                                Collectors.counting()
                        ));

        System.out.println("Stream MapReduce Output:");
        result.forEach((k, v) -> System.out.println(k + " → " + v));
    }
}
