package com.danssion.java.thread;

import org.apache.tools.ant.taskdefs.Sleep;

public class VisableDemo {
//    public static boolean stop = false;
    //保证可见性
    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
//                System.out.println(i);
            }
        }
        ).start();
        Thread.sleep(1000);
        stop = true;
    }
}
