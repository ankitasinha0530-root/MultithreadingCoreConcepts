package com.multithreading.udemy.phaser;

import java.util.concurrent.Phaser;

class MultiPhaseWorker implements Runnable {

    private Phaser phaser;

    public MultiPhaseWorker(Phaser phaser) {
        this.phaser = phaser;
        phaser.register();
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()
                + " Phase 1 started");

        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName()
                + " Phase 2 started");

        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName()
                + " Phase 3 completed");

        phaser.arriveAndDeregister();
    }
}

public class PhaserMultiPhaseExample {

    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);

        for (int i = 0; i < 3; i++) {

            new Thread(
                    new MultiPhaseWorker(phaser))
                    .start();
        }

        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndAwaitAdvance();

        phaser.arriveAndDeregister();
    }
}