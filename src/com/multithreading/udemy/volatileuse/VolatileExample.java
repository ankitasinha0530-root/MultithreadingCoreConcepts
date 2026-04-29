package com.multithreading.udemy.volatileuse;

class VolatileExample {

    private volatile boolean running = true;

    void stop() {
        running = false;
    }

    void runTask() {

        while (running) {
            // running loop
        }

        System.out.println("Stopped");
    }
}
