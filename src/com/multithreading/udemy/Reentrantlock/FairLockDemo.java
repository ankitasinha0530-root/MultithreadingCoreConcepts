package com.multithreading.udemy.Reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class FairLockExample {

    private ReentrantLock lock = new ReentrantLock(true);

    public void access() {

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock");
            Thread.sleep(500);
        } catch (InterruptedException ignored) {

        } finally {
            lock.unlock();
        }
    }
}

public class FairLockDemo {

    public static void main(String[] args) {

        FairLockExample obj = new FairLockExample();

        for (int i = 1; i <= 5; i++) {
            new Thread(obj::access, "Thread-" + i)
                    .start();
        }
    }
}
