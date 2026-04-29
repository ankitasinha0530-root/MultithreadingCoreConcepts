package com.multithreading.udemy.conditionvariable;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ConditionBasicExample {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void waitForSignal() {
        lock.lock();
        try {
            while (!ready) {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " resumed execution");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void sendSignal() {
        lock.lock();
        try {
            ready = true;
            System.out.println(Thread.currentThread().getName() + " sending signal");
            condition.signal();
        }
        finally {
            lock.unlock();
        }
    }
}

public class ConditionBasicDemo {

    public static void main(String[] args) throws InterruptedException {

        ConditionBasicExample conditionBasic = new ConditionBasicExample();
        Thread waiter = new Thread(conditionBasic::waitForSignal, "Waiter");

        Thread signaler = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        conditionBasic.sendSignal();
                    } catch (Exception e) {}
                }, "Signaler");

        waiter.start();
        signaler.start();
    }
}
