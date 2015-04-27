package com.multithreading.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Consumer<C> implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;

	public Consumer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		while (true) {
			try {
				System.out.println("Consumed: " + sharedQueue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
