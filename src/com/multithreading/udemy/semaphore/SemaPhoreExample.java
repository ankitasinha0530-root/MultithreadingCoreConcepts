package com.multithreading.udemy.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{
	INSTANCE;
	
	final Semaphore semaphore = new Semaphore(3, true); // true -  fairness parameter, 3 = allows to access finite number (3 here) of resources
	
	public void download(){
		try {
			semaphore.acquire();
			downloadData();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
	}

	public void downloadData() {
		System.out.println("Downloading  pages from internet");
		try { Thread.sleep(5000); } catch(Exception e) {}
	}
		
}

public class SemaPhoreExample {
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool(); // we have created cached-pool

		for (int i = 0; i < 12; i++) {    //  we have created 12 threads
			service.execute(Downloader.INSTANCE::download);
		}

//		for (int i = 0; i < 12; i++) {    //  we have created 12 threads
//			service.execute(() -> Downloader.INSTANCE.download());
//		}

//		for (int i = 0; i < 12; i++) {    //  we have created 12 threads
//			service.execute(new Runnable() {
//				@Override
//				public void run() {
//					Downloader.INSTANCE.download();
//				}
//			});
//		}

	}

}
