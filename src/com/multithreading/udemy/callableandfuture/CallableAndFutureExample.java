package com.multithreading.udemy.callableandfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements Callable<String>{

	int id;
	
	public Processor (int id) {
		this.id = id;
	}
	@Override
	public String call() throws Exception { // call methods in callable interface, it is generic interface
		Thread.sleep(2000);
		return "id : " + id;
	}
}

public class CallableAndFutureExample {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		List<Future<String>> list = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Future<String> future = executor.submit(new Processor(2000+i)); // submit method used as we want to return result
			list.add(future);
		}
		for(Future<String>  f : list) {
			try {
				f.get();
				System.out.println("Future id : = " + f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
