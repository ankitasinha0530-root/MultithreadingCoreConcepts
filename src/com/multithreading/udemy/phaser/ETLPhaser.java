package com.multithreading.udemy.phaser;

import java.util.concurrent.Phaser;

public class ETLPhaser {

    static Phaser phaser = new Phaser(1); // main thread

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            phaser.register();

            new Thread(() -> {
                load();
                phaser.arriveAndAwaitAdvance();

                process();
                phaser.arriveAndAwaitAdvance();

                save();
                phaser.arriveAndDeregister();
            }).start();
        }

        phaser.arriveAndAwaitAdvance(); // wait phase 0
        phaser.arriveAndAwaitAdvance(); // wait phase 1
        phaser.arriveAndDeregister();
    }

    static void load() { sleep("Loading"); }
    static void process() { sleep("Processing"); }
    static void save() { sleep("Saving"); }

    static void sleep(String stage) {
        System.out.println(Thread.currentThread().getName() + " " + stage);
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
}
