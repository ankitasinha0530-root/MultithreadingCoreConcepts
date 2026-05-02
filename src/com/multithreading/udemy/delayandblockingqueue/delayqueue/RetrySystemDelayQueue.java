package com.multithreading.udemy.delayandblockingqueue.delayqueue;

import java.util.concurrent.*;

class RetryTask implements Delayed {

    private long time;
    private String message;

    public RetryTask(String message, long delay) {

        this.message = message;

        this.time =
                System.currentTimeMillis()
                        + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {

        long diff =
                time
                        - System.currentTimeMillis();

        return unit.convert(
                diff,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public int compareTo(Delayed o) {

        return Long.compare(
                this.time,
                ((RetryTask) o).time
        );
    }

    public String getMessage() {

        return message;
    }
}

public class RetrySystemDelayQueue {

    public static void main(String[] args) throws Exception {

        DelayQueue<RetryTask> queue = new DelayQueue<>();

        queue.put(
                new RetryTask("Retry Msg1", 3000));

        queue.put(
                new RetryTask("Retry Msg2", 1000));

        while (true) {

            RetryTask task =
                    queue.take();

            System.out.println(
                    "Retrying: "
                            + task.getMessage());
        }
    }
}
