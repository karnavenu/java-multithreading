package com.multithreading.threads;

public class OddRunnable implements Runnable {
	Object shared;
	private int number = 1;
	private Thread theCurrentThread;

	public OddRunnable(Object shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		this.theCurrentThread = Thread.currentThread();
		while (number < 50) {

			synchronized (shared) {

				System.out.println("Odd number =  " + number);

				number = number + 2;

				try {

//					Thread.sleep(500); // only to view sequence of execution

					shared.notify();

					shared.wait();

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		}

	}
	
	public void stop(){
		this.theCurrentThread = null;
	}


}
