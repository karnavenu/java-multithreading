package com.multithreading.locks;

import java.util.HashMap;
import java.util.Map;

/*
 *  To make the ReadWriteLock reentrant for readers we 
 *  will first establish the rules for read reentrance:

 A thread is granted read reentrance if it can get read 
 access (no writers or write requests), or if it already 
 has read access (regardless of write requests).

 To determine if a thread has read access already a reference 
 to each thread granted read access is kept in a Map along with
 how many times it has acquired read lock. When determing if read 
 access can be granted this Map will be checked for a reference to 
 the calling thread. Here is how the lockRead() and unlockRead() methods
 looks after that change: 
 */
public class ReadReentrantLock {
	int writers = 0;
	int writeRequests = 0;
	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

	public synchronized void lockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while (!canGrantReadAccess(callingThread)) {
			wait();
		}

		readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
	}

	public synchronized void unLockRead() {
		Thread callingThread = Thread.currentThread();
	    int accessCount = getReadAccessCount(callingThread);
	    if(accessCount == 1){ readingThreads.remove(callingThread); }
	    else { readingThreads.put(callingThread, (accessCount -1)); }
	    notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		while (writers > 0 || !readingThreads.isEmpty()) {
			wait();
		}
		writeRequests--;
		writers++;
	}

	public synchronized void unLockWrite() {
		writers--;
		notifyAll();
	}

	private int getReadAccessCount(Thread callingThread) {
		Integer accessCount = readingThreads.get(callingThread);
		if (accessCount == null)
			return 0;
		return accessCount.intValue();
	}

	private boolean isReader(Thread callingThread) {
		return readingThreads.get(callingThread) != null;
	}

	private boolean canGrantReadAccess(Thread callingThread) {
		if (writers > 0)
			return false;
		if (isReader(callingThread))
			return true;
		if (writeRequests > 0)
			return false;
		return true;
	}
}
