package com.multithreading.udemy.readwritelock;

import java.util.concurrent.locks.*;

public class ReadWriteLockExample {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private int value = 0;

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + value);
            Thread.sleep(500);
        } catch (Exception e) {
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int newValue) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing: " + newValue);
            value = newValue;
            Thread.sleep(500);
        } catch (Exception e) {
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample obj = new ReadWriteLockExample();

        Runnable reader = obj::read;
        Runnable writer = () -> obj.write((int) (Math.random() * 100));

        for (int i = 0; i < 3; i++) new Thread(reader).start();
        for (int i = 0; i < 2; i++) new Thread(writer).start();
    }
}
