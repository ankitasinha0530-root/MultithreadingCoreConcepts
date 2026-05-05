package com.multithreading.udemy.phaser;

import java.util.concurrent.Phaser;

public class EcommercePhaser {

    static Phaser phaser = new Phaser(1);

    public static void main(String[] args) {

        for (int i = 1; i <= 3; i++) {
            int orderId = i;
            phaser.register();

            new Thread(() -> processOrder(orderId)).start();
        }

        // wait all phases
        for (int i = 0; i < 3; i++) {
            phaser.arriveAndAwaitAdvance();
        }

        phaser.arriveAndDeregister();
    }

    static void processOrder(int id) {

        validate(id);
        phaser.arriveAndAwaitAdvance();

        payment(id);
        phaser.arriveAndAwaitAdvance();

        shipping(id);
        phaser.arriveAndDeregister();
    }

    static void validate(int id) { log(id, "Validating"); sleep(); }
    static void payment(int id) { log(id, "Payment"); sleep(); }
    static void shipping(int id) { log(id, "Shipping"); sleep(); }

    static void log(int id, String stage) {
        System.out.println("Order " + id + " → " + stage);
    }

    static void sleep() {
        try { Thread.sleep(500); } catch (Exception e) {}
    }
}
