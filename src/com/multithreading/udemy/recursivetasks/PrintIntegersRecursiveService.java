package com.multithreading.udemy.recursivetasks;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PrintAction extends RecursiveAction { //PrintAction
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> nums;
	
	public PrintAction(List<Integer> nums) {
		this.nums = nums;
	}
	
	@Override
	protected void compute() {
		// the problem is small enough (containing 2 items) so we use
		// standard sequential print operation
		if(nums.size() < 2) {
			for(Integer num : nums)
				System.out.println(num);
		} else {
			// otherwise we split the problem into 2 sub-problems
			// [a,b,c] --> [a] and [b,c]
			// [a,b,c,d] --> [a,b] and [c,d]
			List<Integer> left = nums.subList(0, nums.size()/2);
			List<Integer> right = nums.subList(nums.size()/2,  nums.size());
		
			PrintAction action1 = new PrintAction(left);
			PrintAction action2 = new PrintAction(right);
			
			// it means these actions are thrown into the pool
			// fork-join assigns different threads to different tasks
			// so these will be executed with different treads
			invokeAll(action1, action2);
		}
	}
}

public class PrintIntegersRecursiveService {
	
	public static void main(String[] args) {
		
		int[] arr = {1, 5, 4, 3, 6, 7, 8, 9, 3, 4, 65, 7, 7, 3, 2, 5, 4, 6, 5, 3 ,6 };
		
	//	List list = Arrays.asList(arr);
		List<Integer> list = IntStream.of(arr)
				.boxed() 
				.collect(Collectors.toList());
		
		PrintAction printAction= new PrintAction(list);
		
		ForkJoinPool pool = new ForkJoinPool();
		
		printAction.invoke();
		
	}
}