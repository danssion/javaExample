/**
 * \* Created with IntelliJ IDEA.
 * \* Name: FileLockTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 19:57
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.nio;

import java.nio.channels.*;
import java.io.FileOutputStream;

public class FileLockTest {
    public static void main(String[] args)
            throws Exception {

        try (
                // 使用FileOutputStream获取FileChannel
                FileChannel channel = new FileOutputStream("a.txt")
                        .getChannel()) {
            // 使用非阻塞式方式对指定文件加锁
            FileLock lock = channel.tryLock();
            // 程序暂停10s
            Thread.sleep(10000);
            // 释放锁
            lock.release();
        }
    }
}
