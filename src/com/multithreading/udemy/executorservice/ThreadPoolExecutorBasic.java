package com.multithreading.udemy.executorservice;

import java.util.concurrent.*;

public class ThreadPoolExecutorBasic {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(

                        2,  // corePoolSize minimum pool size when created
                        4,  // maxPoolSize
                        60, // keepAliveTime
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(2) // Stores Waiting Tasks
                );

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " executing task");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        };

        for (int i = 1; i <= 6; i++) {
            executor.execute(task);
        }
        executor.shutdown();
    }
}
/**
  ThreadPoolExecutor(
        corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit,
        BlockingQueue
   )
 */

/**
 Policies
new ThreadPoolExecutor.CallerRunsPolicy()
new ThreadPoolExecutor.DiscardPolicy()
new ThreadPoolExecutor.DiscardOldestPolicy()
*/

/**
Rejection Handler - Caller thread executes task if pool full.
ThreadPoolExecutor executor =
        new ThreadPoolExecutor(

                2,
                4,
                60,
                TimeUnit.SECONDS,

                new ArrayBlockingQueue<>(2),

                new ThreadPoolExecutor.CallerRunsPolicy() -- Caller thread executes task if pool full.
        );
 */

/**
Used in:

REST calls
DB calls
Kafka processing

Very relevant to your backend systems.*/

/**
Monitoring ThreadPoolExecutor ⭐⭐⭐

Very useful in production debugging.
 executor.getPoolSize();
executor.getActiveCount();
executor.getCompletedTaskCount();
executor.getQueue().size();*/

/**
What happens when queue is full?
Answer:If the queue is full and maximumPoolSize is reached, the task is rejected using a rejection policy.
 */

/**
1️⃣2️⃣ Real Backend Use Cases (Very Relevant)

Used in:
        ✔ Kafka consumer parallelism
✔ Batch processing
✔ REST aggregation
✔ Async job execution
✔ Background processing

Given your Kafka + backend work, this is highly practical.
 */
