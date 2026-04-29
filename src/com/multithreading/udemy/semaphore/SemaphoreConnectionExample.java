package com.multithreading.udemy.semaphore;

import java.util.concurrent.Semaphore;

class ConnectionPool {

    private Semaphore semaphore;

    public ConnectionPool(int maxConnections) {
        semaphore = new Semaphore(maxConnections);
    }

    public void connect(String name) {

        try {
            semaphore.acquire();
            System.out.println(name + " acquired connection");
            Thread.sleep(3000);
            System.out.println(name + " releasing connection");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

public class SemaphoreConnectionExample {

    public static void main(String[] args) {

        ConnectionPool pool = new ConnectionPool(2);

        for (int i = 1; i <= 5; i++) {
            int id = i;

            new Thread(() -> pool.connect("Thread-" + id))
                    .start();
        }
    }
}

/**

🔑 Real-world Use

Used in:

Database pools
Kafka connection limits
API throttling
Thread throttling

Very production relevant.
 */
