package com.danssion.java.thread.pool;

public class MyThread extends Thread {

    public Integer num = 0;


    @Override
    public void run() {
        super.run();
        System.out.println(String.format("thread id: %d is run! thread num:%d ", Thread.currentThread().getId(),
                this.num));
    }

    public MyThread setNum(Integer num) {
        this.num = num;
        return this;
    }
}
