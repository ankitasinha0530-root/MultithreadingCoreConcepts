package com.multithreading.udemy.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetExample {

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(10);

        boolean updated = count.compareAndSet(10, 20);
        System.out.println("Updated: " + updated);
        System.out.println("Value: " + count.get());

        boolean updated2 = count.compareAndSet(10, 20); // since expected value should be 20 here as 10 is already updated to 20
        System.out.println("Updated2: " + updated2);
        System.out.println("Value: " + count.get());
    }
}
