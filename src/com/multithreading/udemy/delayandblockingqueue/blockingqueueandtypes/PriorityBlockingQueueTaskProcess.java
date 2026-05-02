package com.multithreading.udemy.delayandblockingqueue.blockingqueueandtypes;

import java.util.concurrent.*;

class Task implements Comparable<Task> {

    int priority;
    String name;

    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority); // Higher priority first
    }
}

public class PriorityBlockingQueueTaskProcess {

    public static void main(String[] args) throws Exception {

        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        queue.put(new Task(1, "Low"));
        queue.put(new Task(5, "High"));
        queue.put(new Task(3, "Medium"));

        while (!queue.isEmpty()) {
            Task task = queue.take();
            System.out.println(task.name + " Priority: " + task.priority);
        }
    }
}
