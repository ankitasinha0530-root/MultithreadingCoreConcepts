package com.multithreading.udemy.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class LatchReuseExample {

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(2);

        latch.countDown();
        latch.countDown();

        latch.await(); // OK

        System.out.println("Latch completed");

        // Trying to reuse (does NOT work)
        latch.await();  // Immediately returns (count already 0)

        // No way to reset latch ❌
/**
        Why reuse fails?

                Because:

                Count = 2 → 1 → 0 → DONE forever

        There is no reset() method.

 ✅ Correct Way — Create New Latch
 CountDownLatch latch1 = new CountDownLatch(2);

 // use latch1

 CountDownLatch latch2 = new CountDownLatch(2);

 // use latch2 again
 */
    }
}
