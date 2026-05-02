package com.multithreading.udemy.forkjoin.forkjoin;

import java.util.concurrent.*;

public class RecurssiveTaskMain {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8};

        ForkJoinPool pool = new ForkJoinPool();

        int result = pool.invoke(new SumTask(arr, 0, arr.length));

        System.out.println(result);
    }
}

class SumTask extends RecursiveTask<Integer> {

    private int[] arr;
    private int start, end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {

        if (end - start <= 2) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }

        int mid = (start + end) / 2;

        SumTask left = new SumTask(arr, start, mid);
        SumTask right = new SumTask(arr, mid, end);


        //👉 This avoids unnecessary thread creation.
        left.fork();          // async  // send to another thread
        int rightResult = right.compute(); // sync // continue in current thread
        int leftResult = left.join(); // wait for result for the task which is forked

        return leftResult + rightResult;
    }
}
