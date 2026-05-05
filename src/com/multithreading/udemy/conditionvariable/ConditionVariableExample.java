package com.multithreading.udemy.conditionvariable;

import java.util.concurrent.locks.*;

public class ConditionVariableExample {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    private int data = 0;
    private boolean available = false;

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (available) {
                notEmpty.await();
            }

            data = value;
            available = true;

            System.out.println("Produced: " + value);

            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (!available) {
                notEmpty.await();
            }

            System.out.println("Consumed: " + data);
            available = false;

            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ConditionVariableExample obj = new ConditionVariableExample();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    obj.produce(i);
                }
            } catch (Exception e) {}
        }).start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    obj.consume();
                }
            } catch (Exception e) {}
        }).start();
    }
}
