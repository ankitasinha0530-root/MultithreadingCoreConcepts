package com.multithreading.udemy.forkjoin.forkjoin.parallelmergerecursivetask;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class MergeSortTask extends RecursiveTask<int[]> {

    private int[] nums;

    private static final int THRESHOLD = 10;

    public MergeSortTask(int[] nums) {
        this.nums = nums;
    }

    @Override
    protected int[] compute() {

        // STEP 1 — Small task → sequential sort
        if (nums.length <= THRESHOLD) {
            Arrays.sort(nums);
            return nums;
        }

        // STEP 2 — Split array
        int middleIndex = nums.length / 2;

        int[] leftSubarray = Arrays.copyOfRange(nums, 0, middleIndex);

        int[] rightSubarray = Arrays.copyOfRange(nums, middleIndex, nums.length);

        // STEP 3 — Create subtasks
        MergeSortTask leftTask = new MergeSortTask(leftSubarray);

        MergeSortTask rightTask = new MergeSortTask(rightSubarray);

        // STEP 4 — Fork left
        leftTask.fork();

        // STEP 5 — Compute right
        int[] rightResult = rightTask.compute();

        // STEP 6 — Join left
        int[] leftResult = leftTask.join();

        // STEP 7 — Merge results
        return merge(leftResult, rightResult);
    }

        private int[] merge(int[] left, int[] right) {

        int[] merged = new int[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {

            if (left[i] < right[j])
                merged[k++] = left[i++];
            else
                merged[k++] = right[j++];
        }

        while (i < left.length)
            merged[k++] = left[i++];

        while (j < right.length)
            merged[k++] = right[j++];

        return merged;
    }
}
/**
merged[k++]

 Use current value of k
 Store value at merged[k]
 Then increment k

 this ++m sign means increment post operation
 */
