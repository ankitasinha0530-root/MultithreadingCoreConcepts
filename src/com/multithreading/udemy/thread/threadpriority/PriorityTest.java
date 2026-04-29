package com.multithreading.udemy.thread.threadpriority;

class PriorityDemo extends Thread {

    public PriorityDemo(String name) {
        super(name);
    }

    @Override
    public void run() {

        long count = 0;
        for (int i = 0; i < 100000000; i++) {
            count++;
        }
        System.out.println(getName() + " completed with priority " + getPriority());
    }
}

public class PriorityTest {

    public static void main(String[] args) {

        PriorityDemo low = new PriorityDemo("Low Priority");
        PriorityDemo high = new PriorityDemo("High Priority");

        low.setPriority(Thread.MIN_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);

        low.start();
        high.start();
    }
}
