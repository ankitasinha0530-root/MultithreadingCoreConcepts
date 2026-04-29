package com.multithreading.udemy.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

class IdGenerator {

    private final AtomicInteger counter = new AtomicInteger(1);

    public int getNextId() {
        return counter.incrementAndGet();
    }
}

public class IdGeneratorDemo {

    public static void main(String[] args) {

        IdGenerator generator = new IdGenerator();

        Runnable task = () -> {
            int id = generator.getNextId();
            System.out.println(Thread.currentThread().getName() + " ID: " + id);};

        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
