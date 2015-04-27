package com.multithreading.pool;

import java.util.concurrent.BlockingQueue;

public class PoolThread extends Thread {

  private BlockingQueue taskQueue = null;
  private boolean       isStopped = false;

  public PoolThread(BlockingQueue queue){
    taskQueue = queue;
  }

  public void run(){
    while(!isStopped()){
      try{
        Runnable runnable = (Runnable) taskQueue.take();
        runnable.run();
      } catch(Exception e){
        //log or otherwise report exception,
        //but keep pool thread alive.
      }
    }
  }

  public synchronized void stopThread(){
    isStopped = true;
  }

  public synchronized boolean isStopped(){
    return isStopped;
  }
}
