package com.danssion.java.nio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/11 下午9:18
 * @desc JavaExample-DdsignPattern
 */
public class NIOSocketServer {

    /**
     * NIO 三个核心
     *
     * channel
     * buffer
     * selector
     */

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//设置连接非阻塞
            serverSocketChannel.socket().bind(new InetSocketAddress(8090

            ));
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();//获得一个客户端链接
                socketChannel.configureBlocking(false);//IO 非阻塞
                if(socketChannel != null) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//用户空间的缓存区
                    int i = socketChannel.read(byteBuffer);
                    if(i > 0) {
                        System.out.println(new String(byteBuffer.array()));
                    } else {
                        Thread.sleep(1000);
                        System.out.println("数据未就绪");
                    }

                    Thread.sleep(10000);
                    byteBuffer.flip();//buffer 数据反转：相当于数组索引更新
                    socketChannel.write(byteBuffer);
                } else {
                    Thread.sleep(1000);
                    System.out.println("我是非阻塞的~！连接未就绪");
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
