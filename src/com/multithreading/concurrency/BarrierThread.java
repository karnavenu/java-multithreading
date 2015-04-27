package com.multithreading.concurrency;

import java.util.concurrent.CyclicBarrier;

public class BarrierThread implements Runnable {
	private CyclicBarrier barrier;

	public BarrierThread(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			String threadName = Thread.currentThread().getName();
			System.out.println("I have arrived" + threadName);
			barrier.await();
			System.out.println("Lets play" + threadName);
		} catch (Exception e) {
			System.out.println("Am exception occured");

		}
	}

}
