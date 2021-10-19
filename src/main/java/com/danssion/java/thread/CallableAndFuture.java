package com.danssion.java.thread;

import java.util.concurrent.*;

public class CallableAndFuture {
    //创建线程类
    public static class CallableTest implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello call !";
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //启动线程
        Future<String> future = threadPool.submit(new CallableTest());
        try {
            System.out.println("waiting thread to finish!");
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
