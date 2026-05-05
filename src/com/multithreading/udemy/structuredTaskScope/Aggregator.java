package com.multithreading.udemy.structuredTaskScope;

import java.util.concurrent.*;

public class Aggregator {

    static String getUser() {
        sleep(2);
        return "User";
    }

    static String getOrders() {
        sleep(3);
        return "Orders";
    }

    static String getPayment() {
        sleep(1);
        return "Payment";
    }

    static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            StructuredTaskScope.Subtask<String> user = scope.fork(Aggregator::getUser);
            StructuredTaskScope.Subtask<String> orders = scope.fork(Aggregator::getOrders);
            StructuredTaskScope.Subtask<String> payment = scope.fork(Aggregator::getPayment);

            scope.join();
            scope.throwIfFailed();

            String response = user.get()
                    + " | " + orders.get()
                    + " | " + payment.get();

            System.out.println(response);
        }
    }
}
