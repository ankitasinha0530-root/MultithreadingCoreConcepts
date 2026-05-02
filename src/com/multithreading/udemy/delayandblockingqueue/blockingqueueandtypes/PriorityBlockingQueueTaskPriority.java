package com.multithreading.udemy.delayandblockingqueue.blockingqueueandtypes;

import java.util.concurrent.*;

class Job implements Comparable<Job> {

    int priority;
    String name;

    public Job(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(other.priority, this.priority);
    }
}

public class PriorityBlockingQueueTaskPriority {

    public static void main(String[] args) throws Exception {

        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        queue.put(new Job(1, "Low Task"));
        queue.put(new Job(5, "High Task"));
        queue.put(new Job(3, "Medium Task"));

        while (!queue.isEmpty()) {
            Job job = queue.take(); // this will extract highest priority task from the queue
            System.out.println("Processing: " + job.name);
        }
    }
}
