package com.multithreading.udemy.abstractqueuedsynchronizer;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

public class MyOwnLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    // other methods simplified
    public void lockInterruptibly() throws InterruptedException {}
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public boolean tryLock(long time, java.util.concurrent.TimeUnit unit) {
        return false;
    }

    public java.util.concurrent.locks.Condition newCondition() {
        return null;
    }
}

/**
🧠 What’s happening?
tryAcquire() → CAS on state
acquire() → handles queue + blocking
release() → wakes next thread
*/
