package com.multithreading.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownTest {
    private static final int MAX_THREADS = 5;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//		normalBarrierImpl();
		countDownTest();
	}

	private static void countDownTest() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(MAX_THREADS);
		
        for(int i=0;i<MAX_THREADS;i++) {
            Thread t = new Thread(new CountDownThread(countDownLatch, String.format("Thread-%d", i)));
            t.start();
        }
        
        System.out.println("Spawning Finished");
        System.out.println("Waiting All Threads to Finish");
        countDownLatch.await(); // Await is void
        System.out.println("All Threads are Finished");


		
	}
}
