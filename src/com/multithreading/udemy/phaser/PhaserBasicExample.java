package com.multithreading.udemy.phaser;

import java.util.concurrent.Phaser;

public class PhaserBasicExample {

    static Phaser phaser = new Phaser(3); // 3 parties

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Phase 0");
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " Phase 1");
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " Phase 2");
            phaser.arriveAndAwaitAdvance();
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}
