package com.multithreading.udemy.thread.threadpriority;

public class ThreadPriorityRealExample {

    public static void main(String[] args) {

        TaskThread paymentThread = new TaskThread("Payment Processing");
        TaskThread reportThread = new TaskThread("Report Generation");
        TaskThread cleanupThread = new TaskThread("Log Cleanup");

        // Set priorities
        paymentThread.setPriority(Thread.MAX_PRIORITY); // 10
        reportThread.setPriority(Thread.NORM_PRIORITY); // 5
        cleanupThread.setPriority(Thread.MIN_PRIORITY); // 1

        paymentThread.start();
        reportThread.start();
        cleanupThread.start();
    }
}

class TaskThread extends Thread {

    public TaskThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " | Priority: " + getPriority() + " | Step: " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
