package com.multithreading.udemy.forkjoin.forkjoin.parallelmergerecursivetask;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {

        int[] nums = initializeNums();

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        MergeSortTask task = new MergeSortTask(nums);

        long start = System.currentTimeMillis();
        int[] sorted = pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("Parallel sort time: " + (end - start) + " ms");
        Arrays.stream(sorted).forEach(ele ->  System.out.print(ele + ", "));
    }

    private static int[] initializeNums() {

        Random random = new Random();

        int[] nums = new int[50];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        return nums;
    }
}
