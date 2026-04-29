package com.multithreading.udemy.thread.manipulate.threadjoin;

// with thread.join() the main caller thread will wait for execution all the threads it has started
// and then the main thread will start executing further
public class RunnableJoinTest2 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runner10());
        Thread t2 = new Thread(new Runner11());

        t1.start();
        t2.start();

        // print the state of all the running threads
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            System.out.println("Thread Name : " + t.getName() + ", state : " + t.getState());
        }
        t1.join();
        t1.join();

        System.out.println("Executing Main after All threads Executed and Terminated ");
    }

	static class Runner10 implements Runnable{

		@Override
		public void run() {
			int i = 0;
			for(i =0; i< 10; i++) {

				System.out.println("Runner1 : " + i);
			}
		}
	}

	static class Runner11 implements Runnable{

		@Override
		public void run() {
			int i = 0;
			for(i = 0; i< 10; i++) {
				System.out.println("Runner2 : " + i);
			}
		}
	}
}
