package com.multithreading.udemy.MapReduce;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericMapReduce {

    public static void main(String[] args) {

        List<String> data = List.of(
                "apple banana apple",
                "banana apple",
                "orange banana"
        );

        Map<String, Long> result = mapReduce(
                data,
                line -> Arrays.stream(line.split(" ")),
                word -> word
        );

        result.forEach((k, v) -> System.out.println(k + " → " + v));
    }

    static <T, K> Map<K, Long> mapReduce(
            List<T> input,
            Function<T, java.util.stream.Stream<K>> mapper,
            Function<K, K> classifier
    ) {
        return input.stream()
                .flatMap(mapper)
                .collect(Collectors.groupingBy(
                        classifier,
                        Collectors.counting()
                ));
    }
}
