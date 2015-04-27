package com.multithreading.threads;

public class EvenRunnable implements Runnable {
	Object shared;
	private int number = 2;
	private Thread theCurrentThread;

	public EvenRunnable(Object shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		this.theCurrentThread = Thread.currentThread();
		while (number < 50) {

			synchronized (shared) {

				System.out.println("Even number =  " + number);

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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
	
	public void stop(){
		this.theCurrentThread = null;
	}

}
