package com.multithreading.udemy.thread.threadpriority;

public class ThreadPriorityExample {
    public static void main(String[] args) {

        MyThread t1 = new MyThread("Thread 1");
        MyThread t2 = new MyThread("Thread 2");
        MyThread t3 = new MyThread("Thread 3");

        // Set priorities
        t1.setPriority(Thread.MIN_PRIORITY);   // 1
        t2.setPriority(Thread.NORM_PRIORITY);  // 5
        t3.setPriority(Thread.MAX_PRIORITY);   // 10

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 But don’t rely on this exact order—thread scheduling depends on the JVM and OS.
Priority is just a hint to the scheduler.
High-priority threads may run first, but not always.
Overusing priorities can make your program unpredictable.
Modern Java apps usually rely on executors instead of manual thread priority.
 */

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " is running with priority " + getPriority());
    }
}

