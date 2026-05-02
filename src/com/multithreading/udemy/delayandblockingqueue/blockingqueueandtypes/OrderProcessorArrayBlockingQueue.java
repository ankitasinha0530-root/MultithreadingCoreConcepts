package com.multithreading.udemy.delayandblockingqueue.blockingqueueandtypes;

import java.util.concurrent.*;

public class OrderProcessorArrayBlockingQueue {

    public static void main(String[] args) {

        BlockingQueue<String> orderQueue = new ArrayBlockingQueue<>(5);

        // Producer (User placing orders)
        Runnable producer = () -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    String order = "Order-" + i;
                    orderQueue.put(order);
                    System.out.println("Placed: " + order);

                } catch (Exception e) {}
            }
        };

        // Consumer (Worker processing orders)
        Runnable consumer = () -> {
            while (true) {
                try {
                    String order = orderQueue.take();
                    System.out.println("Processing: " + order);
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
