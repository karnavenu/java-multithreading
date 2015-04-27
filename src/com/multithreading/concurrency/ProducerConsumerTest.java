package com.multithreading.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		   //Creating shared object
	     BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>(5);
	 
	     //Creating Producer and Consumer Thread
	     Thread prodThread = new Thread(new Producer<Integer>(sharedQueue));
	     Thread consThread = new Thread(new Consumer<Integer>(sharedQueue));

	     //Starting producer and Consumer thread
	     prodThread.start();
	     consThread.start();
	}

}
