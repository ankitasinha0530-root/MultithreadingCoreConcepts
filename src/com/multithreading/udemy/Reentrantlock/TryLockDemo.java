package com.multithreading.udemy.Reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class TryLockExample {

    private ReentrantLock lock = new ReentrantLock();

    public void accessResource() {

        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could not acquire lock");
        }
    }
}

public class TryLockDemo {

    public static void main(String[] args) {

        TryLockExample obj = new TryLockExample();

        Runnable task = obj::accessResource;

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
    }
}
