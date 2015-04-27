package com.multithreading.locks;

public class ReadWriteLock {

	boolean isReadLock = false;
	boolean isWriteLock = false;
	Thread readLockedBy = null;
	Thread writeLockedBy = null;
	int readers = 0;
	int writers = 0;

	public synchronized void readLock() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(isReadLock && readLockedBy!=currentThread){
			wait();
		}
		isReadLock = true;
		readLockedBy = currentThread;
		readers++;

	}

	public synchronized void readUnLock() {
		if(Thread.currentThread() == this.readLockedBy){
			readers--;
			if(readers == 0){
				isReadLock = false;
				readLockedBy = null;
				notify();
			}
		}

	}

	public synchronized void writeLock() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(isWriteLock && writeLockedBy!=currentThread){
			wait();
		}
		isWriteLock = true;
		writeLockedBy = currentThread;
		writers++;
	}

	public synchronized void writeUnLock() {
		if(Thread.currentThread() == this.writeLockedBy){
			writers--;
			if(writers == 0){
				isWriteLock = false;
				writeLockedBy = null;
				notify();
			}
		}

	}

}
