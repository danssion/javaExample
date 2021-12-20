package com.danssion.java.locker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/27 19:05
 * @desc
 */

class ReadWriteTool {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    private int num = 0;

    public void read() {//读的方法
        int cnt = 0;
        while (cnt++ < 3) {
            try {
                readLock.lock();
                System.out.println(Thread.currentThread().getId()
                        + " start to read");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " reading," + num);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }

    public void write() {//写的方法
        int cnt = 0;
        while (cnt++ < 3) {
            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getId()
                        + " start to write");
                Thread.sleep(1000);
                num = (int) (Math.random() * 10);
                System.out.println(Thread.currentThread().getId() + " write," + num);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
                System.out.println(Thread.currentThread().getId() + " write release ");
            }
        }
    }
}

/**
 * 在第18行的 read 方法里，我们是先通过第22行的代码加“读锁“，随后在第15行进行读操作。
 * 在第31行的 write 方法里，我们是先通过第35行的代码加“写锁”，随后在第30行进行写操作
 */


class ReadThread extends Thread {
    private ReadWriteTool readTool;

    public ReadThread(ReadWriteTool readTool) {
        this.readTool = readTool;
    }

    @Override
    public void run() {
        readTool.read();
    }
}

class WriteThread extends Thread {
    private ReadWriteTool writeTool;

    public WriteThread(ReadWriteTool writeTool) {
        this.writeTool = writeTool;
    }

    @Override
    public void run() {
        writeTool.write();
    }
}









