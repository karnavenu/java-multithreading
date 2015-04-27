package com.multithreading.concurrency;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

    class CountDownThread implements Runnable {
        private CountDownLatch countDownLatch;
        
        private String name;
        
        public CountDownThread(CountDownLatch countDownLatch, String name) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }
        
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                
                System.out.printf("%s : Doing Some Work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Doing Some more work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Finished work on %s\n", getFormattedDate(sdf), name);
                countDownLatch.countDown(); 
                System.out.printf("%s : Count Down Latch count on %s is %d\n", getFormattedDate(sdf), name, countDownLatch.getCount());
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

