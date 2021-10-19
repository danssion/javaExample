package com.danssion.java.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("thread body by extends thread");
    }
}
