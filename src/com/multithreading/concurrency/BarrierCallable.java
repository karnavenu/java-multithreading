package com.multithreading.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class BarrierCallable implements Callable<String> {
	private CyclicBarrier barrier;

	public BarrierCallable(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public String call() throws Exception {
		String threadName = Thread.currentThread().getName();
		System.out.println("I have arrived" + threadName);
		barrier.await();
		System.out.println("Lets play" + threadName);
		return threadName;

	}

}
