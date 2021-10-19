/**
 * \* Created with IntelliJ IDEA.
 * \* Name: WatchServiceTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 20:10
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.nio;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class WatchServiceTest {
    public static void main(String[] args)
            throws Exception
    {
        // 获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault()
                .newWatchService();
        // 为C:盘根路径注册监听
        Paths.get("C:/").register(watchService
                , StandardWatchEventKinds.ENTRY_CREATE
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_DELETE);
        while(true)
        {
            // 获取下一个文件改动事件
            WatchKey key = watchService.take();    //①
            for (WatchEvent<?> event : key.pollEvents())
            {
                System.out.println(event.context() +" 文件发生了 "
                        + event.kind()+ "事件！");
            }
            // 重设WatchKey
            boolean valid = key.reset();
            // 如果重设失败，退出监听
            if (!valid)
            {
                break;
            }
        }
    }
}
