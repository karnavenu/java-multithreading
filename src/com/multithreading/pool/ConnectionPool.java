package com.multithreading.pool;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
	private BlockingQueue<Runnable> taskQueue = null;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	private boolean isStopped = false;

	public ConnectionPool(int size) {

		taskQueue = new LinkedBlockingQueue<Runnable>(size);
		for (int i = 0; i < size; i++) {
			connections.add(new ConnectionThread(taskQueue));
		}
		for (ConnectionThread thread : connections) {
			thread.start();
		}
	}

	public synchronized void execute(Runnable task) throws InterruptedException {
		if (this.isStopped)
			throw new IllegalStateException("ThreadPool is stopped");

		this.taskQueue.put(task);
	}

	public synchronized void stop() {
		this.isStopped = true;
		for (ConnectionThread connection : connections) {
			connection.stopThread();
			connection.stop();
		}
	}
	
	public ConnectionThread getConnection(){
		return new ConnectionThread(taskQueue);
	}
}
