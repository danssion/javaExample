package com.danssion.java.thread;

public class AtomicDemo {
    private static int count = 0;

    public synchronized static void incr() {
        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(
                    () -> AtomicDemo.incr()
            ).start();
        }
        Thread.sleep(2000);
        System.out.println("result:" + count);
    }
}
