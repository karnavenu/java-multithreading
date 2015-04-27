package com.multithreading.locks;

import java.util.HashMap;
import java.util.Map;

/*
 * Write reentrance is granted only if the thread has already write access.
 *  Here is how the lockWrite() and unlockWrite() methods look after that change: 
 */
public class WriteReentrantLock {
	int writeAccesses = 0;
	int writeRequests = 0;
	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
	private Thread writingThread = null;
	
	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		Thread callingThread = Thread.currentThread();
		while (!canGrantWriteAccess(callingThread)) {
			wait();
		}
		writeRequests--;
		writeAccesses++;
		writingThread = callingThread;
	}

	public synchronized void unLockWrite() {
		writeAccesses--;
	    if(writeAccesses == 0){
	      writingThread = null;
	    }
	    notifyAll();
	}


	private boolean canGrantWriteAccess(Thread callingThread){
	    if(hasReaders())             return false;
	    if(writingThread == null)    return true;
	    if(!isWriter(callingThread)) return false;
	    return true;
	  }

	  private boolean hasReaders(){
	    return readingThreads.size() > 0;
	  }

	  private boolean isWriter(Thread callingThread){
	    return writingThread == callingThread;
	  }
}
