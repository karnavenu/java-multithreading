package com.multithreading.locks;

public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new JavaThread();
		thread.setName("Venu");
		thread.start();
	}
}
