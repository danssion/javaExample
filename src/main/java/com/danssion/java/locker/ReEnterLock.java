package com.danssion.java.locker;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/27 18:57
 * @desc
 */
public class ReEnterLock {
    public static void main(String[] args) {
        LockReEnter demo = new LockReEnter();
        new Thread(demo).start();
        new Thread(demo).start();
    }
}

class LockReEnter implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    public void get() {
        lock.lock();
        System.out.print(Thread.currentThread().getId()+"get; \t");
        // 在get方法里调用set
        set();
        lock.unlock();
    }
    public void set() {
        lock.lock();
        System.out.print(Thread.currentThread().getId() + "set; \t");
        lock.unlock();
    }

    @Override
    public void run()
    { get(); }
}
