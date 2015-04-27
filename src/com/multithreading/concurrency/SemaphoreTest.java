package com.multithreading.concurrency;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static final int MAX_THREADS = 5;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		semaphoreTest();

	}

	private static void semaphoreTest() throws InterruptedException {
		Semaphore semaphore = new Semaphore(0);
		System.out.println("Spawning Threads");
        int threadCount = 0;
        Random random = new Random();

        for(int i=0;i<MAX_THREADS;i++) {
            // Threads created will not always be MAX_THREADS
            // Because Threads are created only if Random no is Even.
            // Thus the No of Threads unknown at Semaphore Initialization
            if(random.nextInt(9999) % 2 == 0) {
                Thread t = new Thread(new SemaphoreThread(semaphore, String.format("Thread-%d", i)));
                t.start();
                threadCount++;
            }
        }
        System.out.println("Spawning Finished");
        System.out.println("Waiting All Threads to Finish");
        semaphore.acquire(threadCount); 
        System.out.println("All Threads are Finished");

	}

}
