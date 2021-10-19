package com.danssion.java.nio.reactorThread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/18 下午10:23
 * @desc JavaExample-DdsignPattern
 */
public class MultipleDispathHandle implements Runnable {
    SocketChannel channel;

    private Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public MultipleDispathHandle(SocketChannel channel) {
        this.channel = channel;
    }


    @Override
    public void run() {
        processor();
    }


    private void processor() {
        executor.execute(new ReaderHandler(channel));
    }

    static class ReaderHandler implements Runnable {
        private SocketChannel channel;

        ReaderHandler(SocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"------");
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int len=0,total=0;
            String msg = "";
            try {
                do {
                    len = channel.read(byteBuffer);
                    if(len > 0) {
                        total += len;
                        msg += new String(byteBuffer.array());
                    }
                } while (len > byteBuffer.capacity());
                System.out.println("total:"+total);
                System.out.println(channel.getRemoteAddress()+": Server receive msg:"+msg);
                //后续处理数据，比如：msg=通讯报文
                //登录信息
                //ServerRequest username   password
                //数据库判断
                //返回数据给客户端

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
