package com.multithreading.udemy.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class ResetWorker implements Runnable {

    private CyclicBarrier barrier;

    public ResetWorker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {

        try {

            System.out.println(Thread.currentThread().getName() + " doing work");

            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + " waiting at barrier");

            barrier.await();

            System.out.println(Thread.currentThread().getName() + " crossed barrier");

        } catch (BrokenBarrierException e) {

            System.out.println(Thread.currentThread().getName() + " barrier broken!");

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}

public class CyclicBarrierWithResetExample {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println(
                                "All threads reached barrier"));

        Thread t1 = new Thread(new ResetWorker(barrier));

        Thread t2 = new Thread(new ResetWorker(barrier));

        Thread t3 = new Thread(new ResetWorker(barrier));

        t1.start();
        t2.start();

        // Reset barrier before third thread arrives
        Thread.sleep(1000);

        System.out.println("Main resetting barrier...");

        barrier.reset();

        t3.start();
    }
}

/**
barrier.await();      // wait for all threads
barrier.reset();      // reset barrier
barrier.getNumberWaiting();
barrier.isBroken();
 */
