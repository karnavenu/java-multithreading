package com.multithreading.locks;

public class ReadWriteLock1 {
	int readers = 0;
	int writers = 0;
	int writeRequests =0;
	
	public synchronized void lockRead() throws InterruptedException{
		while(writers >0 || writeRequests>0){
			wait();
		}
		readers++;
	}
	
	public synchronized void unLockRead(){
		readers--;
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException{
		writeRequests++;
		while(writers >0 || readers>0){
			wait();
		}
		writeRequests--;
		writers++;
	}
	
	public synchronized void unLockWrite(){
		writers--;
		notifyAll();
	}

}
