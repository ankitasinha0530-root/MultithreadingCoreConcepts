package com.multithreading.udemy.conditionvariable;

import java.util.concurrent.locks.*;

class SignalAllExample {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " waiting");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " resumed");

        } catch (InterruptedException e) {}
        finally {
            lock.unlock();
        }
    }

    public void signalAllThreads() {
        lock.lock();
        try {
            System.out.println("Signaling all threads");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

public class SignalAllDemo {

    public static void main(String[] args) throws InterruptedException {

        SignalAllExample obj = new SignalAllExample();
        for (int i = 1; i <= 3; i++) {
            new Thread(obj::awaitMethod, "Thread-" + i).start();
        }
        Thread.sleep(2000);
        obj.signalAllThreads();
    }
}
