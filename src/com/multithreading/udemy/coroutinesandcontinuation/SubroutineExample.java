package com.multithreading.udemy.coroutinesandcontinuation;

public class SubroutineExample {

    public static void main(String[] args) {
        System.out.println("Start");
        task();
        System.out.println("End");
    }

    static void task() {
        System.out.println("Step 1");
        sleep(1000);
        System.out.println("Step 2");
        sleep(1000);
        System.out.println("Step 3");
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {}
    }
}

/**
🧠 Behavior
Blocks thread
No pause/resume control
Stack is continuous
 */
