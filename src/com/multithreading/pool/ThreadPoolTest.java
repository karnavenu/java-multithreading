package com.multithreading.pool;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool(20, 10);
	
		
		for (int i = 0; i < 100; i++) {
			try {
				pool.execute(new Runnable(){
					public void run(){
						System.out.println(Thread.currentThread());
					}
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		pool.stop();


	}

}
