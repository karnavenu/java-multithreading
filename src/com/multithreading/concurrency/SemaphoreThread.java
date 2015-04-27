package com.multithreading.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreThread implements Runnable{
	private Semaphore semaphore;
    
    private String name;

	
	public SemaphoreThread(Semaphore semaphore, String name) {
        this.name = name;
        this.semaphore = semaphore;
    }

	@Override
	public void run() {
        try {                
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                
            System.out.printf("%s : Doing Some Work on %s\n", getFormattedDate(sdf), name);
            Thread.sleep(getRandomWaitTime());
            System.out.printf("%s : Doing Some more work on %s\n", getFormattedDate(sdf), name);
            Thread.sleep(getRandomWaitTime());
            System.out.printf("%s : Finished work on %s\n", getFormattedDate(sdf), name);
            semaphore.release();                
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getFormattedDate(SimpleDateFormat sdf) {
        return sdf.format(new Date());
    }
    
    private int getRandomWaitTime() {
        return (int) ((Math.random() + 1) * 1000);
    }

}
