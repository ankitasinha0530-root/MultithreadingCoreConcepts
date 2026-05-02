package com.multithreading.udemy.executorservice.exceptionhandling;

import java.util.concurrent.*;

public class AfterExecuteExample {

    public static void main(String[] args) {

        ExecutorService executor = new LoggingThreadPoolExecutor(
                        2, 2,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>());

        executor.submit(() -> {throw new RuntimeException("Failure inside submit");
        });
        executor.shutdown();
    }
}

class LoggingThreadPoolExecutor extends ThreadPoolExecutor {

    public LoggingThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {

        super(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        super.afterExecute(r, t);
        if (t != null) {
            System.out.println("afterExecute caught: " + t);
        }

        if (r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            }
            catch (ExecutionException e) {
                System.out.println("afterExecute Future error: " + e.getCause());
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}