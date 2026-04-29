package com.multithreading.udemy.conditionvariable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;

class Buffer {

    private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 5;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.add(value);
            System.out.println("Produced: " + value);
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            int value = queue.poll();
            System.out.println("Consumed: " + value);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerConsumerConditionDemo {

    public static void main(String[] args) {

        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            buffer.produce(i);
                        } catch (Exception e) {}
                    }
                });

        Thread consumer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        buffer.consume();
                        } catch (Exception e) {}
                    }

                });

        producer.start();
        consumer.start();
    }
}