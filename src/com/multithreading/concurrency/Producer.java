package com.multithreading.concurrency;

import java.util.concurrent.BlockingQueue;

public class Producer<P> implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;

	public Producer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			System.out.println("Produced: " + i);
			try {
				sharedQueue.put(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
