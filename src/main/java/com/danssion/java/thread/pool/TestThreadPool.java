package com.danssion.java.thread.pool;

import java.util.concurrent.*;

public class TestThreadPool {

    public static void main(String[] args) {
        TestThreadPool testThreadPool = new TestThreadPool();
        testThreadPool.testFixedPool();
//        testThreadPool.testCachePool();
//        testThreadPool.testScheduledPool();
    }

    private void testFixedPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //将线程放入线程池运行
        pool.execute(new MyThread().setNum(1));
        pool.execute(new MyThread().setNum(2));
        pool.execute(new MyThread().setNum(3));
        pool.execute(new MyThread().setNum(0));
        pool.execute(new MyThread().setNum(4));
        pool.execute(new MyThread().setNum(5));
        pool.execute(new MyThread().setNum(6));
        pool.execute(new MyThread().setNum(7));
        pool.shutdown();
    }

    private void testCachePool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        //将线程放入线程池运行
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();
    }

    private void testScheduledPool() {
        class newMyThread extends MyThread {
            @Override
            public void run() {
                super.run();
                System.out.println(Thread.currentThread().getId() + " timestamp:" + System.currentTimeMillis());
            }
        }

        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
        //每隔一段时间执行一次
        exec.scheduleAtFixedRate(new newMyThread(), 0, 3000, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(new newMyThread(), 0, 2000, TimeUnit.MILLISECONDS);
    }
}
