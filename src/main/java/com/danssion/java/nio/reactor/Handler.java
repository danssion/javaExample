package com.danssion.java.nio.reactor;

import com.danssion.java.construct.Ex;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/13 下午9:35
 * @desc JavaExample-DdsignPattern
 */
public class Handler implements Runnable {
    SocketChannel channel;

    public Handler(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "------");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        /** 如果一个Handler 出现阻塞了，整个系统阻塞 **/
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int len = 0, total = 0;
        String msg = "";
        try {
            do {
                len = channel.read(byteBuffer);
                if (len > 0) {
                    total += len;
                    msg += new String(byteBuffer.array());
                }
            } while (len > byteBuffer.capacity());
            System.out.println("total:" + total);
            System.out.println(channel.getRemoteAddress() + ": Server receive msg:" + msg);
            //后续处理数据，比如：msg=通讯报文
            //登录信息
            //ServerRequest username   password
            //数据库判断
            //返回数据给客户端

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
