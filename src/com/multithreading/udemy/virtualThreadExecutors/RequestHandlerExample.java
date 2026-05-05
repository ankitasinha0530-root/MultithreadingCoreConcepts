package com.multithreading.udemy.virtualThreadExecutors;

import java.util.concurrent.*;

public class RequestHandlerExample {

    public static void main(String[] args) {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                int requestId = i;
                executor.submit(() -> handleRequest(requestId));
            }
        }
    }

    static void handleRequest(int requestId) {
        try {
            String apiData = callExternalAPI(requestId);
            String dbData = fetchFromDB(apiData);
            process(dbData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String callExternalAPI(int id) throws InterruptedException {
        Thread.sleep(200); // simulate network
        return "API_DATA_" + id;
    }

    static String fetchFromDB(String input) throws InterruptedException {
        Thread.sleep(300); // simulate DB
        return "DB_DATA_" + input;
    }

    static void process(String data) {
        System.out.println("Processed: " + data + " on " + Thread.currentThread());
    }
}
