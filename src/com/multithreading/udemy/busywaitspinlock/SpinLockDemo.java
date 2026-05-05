package com.multithreading.udemy.busywaitspinlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockDemo {
    static SpinLock lock = new SpinLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " trying to acquire lock");

            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                Thread.sleep(1000); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released lock");
            }
        };

        new Thread(task).start();
        new Thread(task).start();
    }
}

class SpinLock {
    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) {
            // Busy wait (spin)
        }
    }

    public void unlock() {
        lock.set(false);
    }
}
