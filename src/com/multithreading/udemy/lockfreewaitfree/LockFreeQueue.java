package com.multithreading.udemy.lockfreewaitfree;

import java.util.concurrent.atomic.AtomicReference;

class LockFreeQueue<T> {

    static class Node<T> {
        T value;
        AtomicReference<Node<T>> next = new AtomicReference<>(null);

        Node(T value) {
            this.value = value;
        }
    }

    private AtomicReference<Node<T>> head;
    private AtomicReference<Node<T>> tail;

    public LockFreeQueue() {
        Node<T> dummy = new Node<>(null);
        head = new AtomicReference<>(dummy);
        tail = new AtomicReference<>(dummy);
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        while (true) {
            Node<T> last = tail.get();
            Node<T> next = last.next.get();

            if (last == tail.get()) {
                if (next == null) {
                    if (last.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(last, newNode);
                        return;
                    }
                } else {
                    tail.compareAndSet(last, next);
                }
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> first = head.get();
            Node<T> last = tail.get();
            Node<T> next = first.next.get();

            if (first == head.get()) {
                if (first == last) {
                    if (next == null) return null;
                    tail.compareAndSet(last, next);
                } else {
                    T value = next.value;
                    if (head.compareAndSet(first, next)) {
                        return value;
                    }
                }
            }
        }
    }
}

/**
🔁 Why This Is Lock-Free
No locks
CAS retry loops
At least one thread always progresses
 */
