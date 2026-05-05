package com.multithreading.udemy.phaser;

import java.util.concurrent.Phaser;

public class DynamicPhaser {

    static Phaser phaser = new Phaser(1);

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {

                phaser.register(); // dynamic join

                System.out.println(Thread.currentThread().getName() + " joined");

                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + " done");

                phaser.arriveAndDeregister(); // dynamic leave

            }).start();
        }

        sleep(2000);

        System.out.println("Main advancing phase");
        phaser.arriveAndAwaitAdvance();

        phaser.arriveAndDeregister();
    }

    static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}
