package com.multithreading.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CyclicBarrierTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		normalBarrierImpl();
		barrierAndFuturesImpl();
	}

	private static void normalBarrierImpl() {
		CyclicBarrier barrier =  new CyclicBarrier(3, new Runnable(){
            @Override
            public void run(){
                //This task will be executed once all thread reaches barrier
                System.out.println("All parties are arrived at barrier, lets play");
            }
        });
		Thread player1 = new Thread(new BarrierThread(barrier), "Player1");
		Thread player2 = new Thread(new BarrierThread(barrier), "Player2");
		Thread player3 = new Thread(new BarrierThread(barrier), "Player3");

		player1.start();
		player2.start();
		player3.start();
		
	
	}

	private static void barrierAndFuturesImpl() {

		CyclicBarrier barrier =  new CyclicBarrier(3, new Runnable(){
            @Override
            public void run(){
                //This task will be executed once all thread reaches barrier
                System.out.println("All parties are arrived at barrier, lets play");
            }
        });
		ExecutorService service = Executors.newFixedThreadPool(3);
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 3; i++) {
			Future<String> futureObj = service.submit(new BarrierCallable(
					barrier));
			list.add(futureObj);
		}
		for (Future<String> futureObj : list) {
			String str = "";
			try {
				str = futureObj.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(str);
		}
	}
}
