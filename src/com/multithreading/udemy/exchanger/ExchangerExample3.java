package com.multithreading.udemy.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample3 {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        Thread threadA = new Thread(() -> {
            try {
                String data = "Data from Thread A";
                System.out.println("Thread A sending: " + data);
                data = exchanger.exchange(data);
                System.out.println("Thread A received: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                String data = "Data from Thread B";
                System.out.println("Thread B sending: " + data);
                data = exchanger.exchange(data);
                System.out.println("Thread B received: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
    }
}
