package com.multithreading.concurrency;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<String> {

	@Override
	public String call() throws Exception {
		String threadName = Thread.currentThread().getName();
		if(threadName.equals("pool-1-thread-1")){
			Thread.sleep(30000);
		}
		System.out.println("Inside callable: "
				+threadName );
		
		return threadName;
	}

}
