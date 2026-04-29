package com.multithreading.udemy.recursivetasks;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciUsingRecusrsiveTask {
	
	public static void main(String[] args) {
	
	// F(N) = F(N-1) + F(N-2) this is recursive formula
	
	ForkJoinPool pool = new ForkJoinPool();
	System.out.println(pool.invoke(new FibonacciTask(25)));
	}
}

class FibonacciTask extends RecursiveTask<Integer> {
	 
    private int n;
	
    public FibonacciTask(int n){
        this.n = n;
    }
 
    @Override
    protected Integer compute() {
        
    	// F(0) = F(1) = 0
    	if(n <= 1)
            return n;
 
    	FibonacciTask fib1 = new FibonacciTask(n-1);
        FibonacciTask fib2 = new FibonacciTask(n-2);
        
      //  fib1.fork();
        fib2.fork();
     // return fib1.join()+ fib2.join();
        
        // below code is for optimizations
        
        // the actual thread executes the fib1
        // and we create another thread (insert it into the pool) which is 
        // associated with fib2
 

        return fib1.compute()+ fib2.join();
    }
}



