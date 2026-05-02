package com.multithreading.udemy.forkjoin.forkjoin.max1;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {
		
		long[] nums = createNumbers(500000000);
		
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

		// Time taken by sequential max finding
		SequentialMaxFinding sequential = new SequentialMaxFinding();
		long start = System.currentTimeMillis();
		System.out.println("Max: " + sequential.max(nums));
		System.out.println("Time: " + (System.currentTimeMillis() - start));

		// Time taken by fork join parallel max finding
		ParallelMaxTask parallelTask = new ParallelMaxTask(nums, 0, nums.length);
		start = System.currentTimeMillis();
		System.out.println("Max: " + pool.invoke(parallelTask));
		System.out.println("Time: " + (System.currentTimeMillis() - start));
	}
	
	private static long[] createNumbers(int n) {
		
		Random random = new Random();
		
		long[] nums = new long[n];
		
		for(int i=0;i<nums.length;++i)
			nums[i] = random.nextInt(1000);
		
		return nums;
	}
}
