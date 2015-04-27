package com.multithreading.threads;

public class EvenOddTest {
    public static void main(String[] args) {
        Object shared = new Object();
        EvenRunnable evenRunnable = new EvenRunnable(shared);
        OddRunnable oddRunnable = new OddRunnable(shared);
        Thread evenThread = new Thread(evenRunnable, "evenThread");
        Thread oddThread = new Thread(oddRunnable, "oddThread");
        oddThread.start();
        evenThread.start();
        
    }
}
