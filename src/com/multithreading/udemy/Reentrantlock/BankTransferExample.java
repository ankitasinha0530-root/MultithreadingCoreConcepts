package com.multithreading.udemy.Reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    private int balance;

    private ReentrantLock lock = new ReentrantLock();

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {

        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {

        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        return balance;
    }
}

public class BankTransferExample {

    public static void main(String[] args) throws InterruptedException {

        BankAccount account = new BankAccount(1000);

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                account.deposit(10);
                account.withdraw(10);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Balance: " + account.getBalance());
    }
}
