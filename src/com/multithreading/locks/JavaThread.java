package com.multithreading.locks;

public class JavaThread extends Thread {

	@Override
	public void run() {
		System.out.println("Run There"+Thread.currentThread().getName());
		
	}
	

	@Override
	public void start() {
		System.out.println("Run There"+Thread.currentThread().getName());
		
	}
}
