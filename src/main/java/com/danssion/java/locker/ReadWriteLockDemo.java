package com.danssion.java.locker;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/27 19:38
 * @desc
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteTool tool = new ReadWriteTool();
        for (int i = 0; i < 2; i++) {
            new ReadThread(tool).start();
            new WriteThread(tool).start();
        }
    }
}
