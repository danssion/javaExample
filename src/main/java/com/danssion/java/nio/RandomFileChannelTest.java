/**
 * \* Created with IntelliJ IDEA.
 * \* Name: RandomFileChannelTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 19:31
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class RandomFileChannelTest {
    public static void main(String[] args)
            throws IOException
    {
        File f = new File("output0.txt");
        try(
                // 创建一个RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                // 获取RandomAccessFile对应的Channel
                FileChannel randomChannel = raf.getChannel() )
        {
            // 将Channel中所有数据映射成ByteBuffer
            ByteBuffer buffer = randomChannel.map(FileChannel
                    .MapMode.READ_ONLY, 0 , f.length());
            // 把Channel的记录指针移动到最后
            randomChannel.position(f.length());
            // 将buffer中所有数据输出
            randomChannel.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
