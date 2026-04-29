package com.multithreading.udemy.livelock;

import java.util.concurrent.locks.ReentrantLock;

class Account {

    private int balance;
    private ReentrantLock lock = new ReentrantLock();

    public Account(int balance) {
        this.balance = balance;
    }

    public boolean tryTransfer(Account account, int amount) throws InterruptedException {
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (account.lock.tryLock()) {
                        try {
                            if (balance >= amount) {
                                balance -= amount;
                                account.balance += amount;

                                System.out.println(Thread.currentThread().getName() + " transferred " + amount);
                                return true;
                            }
                        } finally {
                            account.lock.unlock();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
            // Both threads retry immediately
            // causing livelock
            Thread.sleep(50);
        }
    }
}

public class LivelockAccountExample {

    public static void main(String[] args) {
        Account acc1 = new Account(1000);
        Account acc2 = new Account(1000);
        Thread t1 = new Thread(() -> {
                    try {
                        acc1.tryTransfer(acc2, 100);
                    } catch (Exception e) {}
                }, "Thread-1");

        Thread t2 = new Thread(() -> {
                    try {
                        acc2.tryTransfer(acc1, 200);
                    } catch (Exception e) {}

                }, "Thread-2");

        t1.start();
        t2.start();
    }
}
