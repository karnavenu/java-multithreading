package com.multithreading.pool;

import java.util.concurrent.BlockingQueue;

public class ConnectionThread extends Thread {

	private BlockingQueue<Runnable> taskQueue = null;
	private boolean isStopped = false;

	public ConnectionThread(BlockingQueue<Runnable> queue) {
		taskQueue = queue;
	}

	public void run() {
		while (!isStopped()) {
			try {
				Runnable runnable = taskQueue.take();
				runnable.run();
			} catch (Exception e) {
				// log or otherwise report exception,
				// but keep pool thread alive.
			}
		}
	}

	public synchronized void stopThread() {
		isStopped = true;
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}
}
