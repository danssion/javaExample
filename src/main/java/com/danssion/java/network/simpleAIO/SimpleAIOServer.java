/**
 * \* Created with IntelliJ IDEA.
 * \* Name: SimpleAIOServer
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 20:14
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.network.simpleAIO;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;

public class SimpleAIOServer {
    static final int PORT = 30000;
    public static void main(String[] args)
            throws Exception
    {
        try(
                // ①创建AsynchronousServerSocketChannel对象。
                AsynchronousServerSocketChannel serverChannel =
                        AsynchronousServerSocketChannel.open())
        {
            // ②指定在指定地址、端口监听。
            serverChannel.bind(new InetSocketAddress(PORT));
            while (true)
            {
                // ③采用循环接受来自客户端的连接
                Future<AsynchronousSocketChannel> future
                        = serverChannel.accept();
                // 获取连接完成后返回的AsynchronousSocketChannel
                AsynchronousSocketChannel socketChannel = future.get();
                // 执行输出。
                socketChannel.write(ByteBuffer.wrap("欢迎你来自AIO的世界！"
                        .getBytes("UTF-8"))).get();
            }
        }
    }
}
