/**
package com.multithreading.udemy.exchanger;

Exchanger<String> exchanger = new Exchanger<>();

Runnable task = () -> {
    try {
        String name = Thread.currentThread().getName();
        String received = exchanger.exchange(name);
        System.out.println(name + " exchanged with " + received);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
};

new Thread(task, "Thread-A").start();
new Thread(task, "Thread-B").start();
new Thread(task, "Thread-C").start();
new Thread(task, "Thread-D").start();
 */
