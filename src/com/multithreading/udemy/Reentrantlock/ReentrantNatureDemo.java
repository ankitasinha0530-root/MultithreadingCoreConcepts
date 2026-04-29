package com.multithreading.udemy.Reentrantlock;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantExample {

    private ReentrantLock lock = new ReentrantLock();

    public void outer() {
        lock.lock();
        try {
            System.out.println("Outer method");
            inner();
        } finally {
            lock.unlock();
        }
    }

    public void inner() {
        lock.lock();
        try {
            System.out.println("Inner method");
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantNatureDemo {

    public static void main(String[] args) {

        ReentrantExample obj = new ReentrantExample();
        obj.outer();
    }
}
