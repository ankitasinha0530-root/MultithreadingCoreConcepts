package com.multithreading.udemy.Reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class TimeoutLockExample {

    private ReentrantLock lock =
            new ReentrantLock();

    public void process() {

        try {

            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " acquired lock");
                    Thread.sleep(3000);
                }
                finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " timeout waiting for lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TimeoutLockDemo {

    public static void main(String[] args) {

        TimeoutLockExample obj = new TimeoutLockExample();

        new Thread(obj::process).start();
        new Thread(obj::process).start();
    }
}