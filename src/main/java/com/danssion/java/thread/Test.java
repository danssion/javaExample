package com.danssion.java.thread;

public class Test {
    public static void main(String[] args) {
        ThreadRunnable runnable = new ThreadRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        MyThread myThread = new MyThread();
        myThread.start();
    }
}
