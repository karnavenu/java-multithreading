package com.multithreading.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		// CallableImpl callable = new CallableImpl();
		// callable.call();
		
		/*execution service is that the future object get method waits on that particular thread(it kind of uses ArrayList) 
		 * 
		 *completion service future object get method doesn't wait on that particular thread(it kind of uses BlockingQueue)
		 *
		 * http://markusjais.com/understanding-java-util-concurrent-completionservice/
		 * http://rdafbn.blogspot.in/2013/01/executorservice-vs-completionservice-vs.html
		 */

		futuresImpl();
//		completionServiceImpl();
	}

	private static void completionServiceImpl() {

		ExecutorService service = Executors.newFixedThreadPool(3);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(
				service);
		List<Callable<String>> list = new ArrayList<Callable<String>>();
		String sum = "";
		for (int i = 0; i < 4; i++) {
			CallableImpl callable = new CallableImpl();
			list.add(callable);
			completionService.submit(callable);
		}

		System.out.println("I am here");
		for (Callable<String> callable : list) {
			try {
				System.out.println("Beforefutures:");
				Future<String> futureObj = completionService.take();
				String value = futureObj.get();
				System.out.println("value:" + value);
				sum += value;
			} catch (InterruptedException e) {
				System.out.println("InterruptedException" + e.getMessage());
			} catch (ExecutionException e) {
				System.out.println("ExecutionException" + e.getMessage());
			}
			service.shutdown();
			// catch (java.util.concurrent.TimeoutException e) {
			// System.out.println("TimeoutException" + e.getMessage());
			// // futureObj.cancel(true);
			// }
		}

	}

	private static void futuresImpl() {
		ExecutorService service = Executors.newFixedThreadPool(3);
		String sum = "";
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 4; i++) {
			CallableImpl callable = new CallableImpl();
			list.add(service.submit(callable));
		}
		System.out.println("I am here");
		for (Future<String> futureObj : list) {
			try {
				System.out.println("Beforefutures:");
				String value = futureObj.get();
				System.out.println("value:" + value);
				sum += value;
			} catch (InterruptedException e) {
				System.out.println("InterruptedException" + e.getMessage());
			} catch (ExecutionException e) {
				System.out.println("ExecutionException" + e.getMessage());
			}
			
			service.shutdown();
			// catch (java.util.concurrent.TimeoutException e) {
			// System.out.println("TimeoutException" + e.getMessage());
			// // futureObj.cancel(true);
			// }
		}

	}

}
