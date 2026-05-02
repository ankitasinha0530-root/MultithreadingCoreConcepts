package com.multithreading.udemy.exchanger;

import java.util.concurrent.Exchanger;

class Buffer {
    int[] data = new int[5];
}

public class BufferExchangeExample {

    public static void main(String[] args) {

        Exchanger<Buffer> exchanger = new Exchanger<>();
        Buffer emptyBuffer = new Buffer();
        Buffer fullBuffer = new Buffer();
        new Thread(() -> {
            Buffer buffer = emptyBuffer;
            try {
                while (true) {
                    // fill buffer
                    for (int i = 0; i < buffer.data.length; i++) {
                        buffer.data[i] = i;
                    }
                    System.out.println("Producer filled buffer");
                    buffer = exchanger.exchange(buffer);
                }
            } catch (InterruptedException e) { }

        }).start();


        new Thread(() -> {
            Buffer buffer = fullBuffer;
            try {
                while (true) {
                    buffer = exchanger.exchange(buffer);
                    System.out.println("Consumer received buffer");
                }
            } catch (InterruptedException e) { }
        }).start();
    }
}
