package com.danssion.java.nio.reactorThread;

import com.danssion.java.nio.reactor.Handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/12 下午10:42
 * @desc JavaExample-DdsignPattern
 */
public class MultiAcceptor implements Runnable {
    final private Selector selector;
    final private ServerSocketChannel serverSocketChannel;

    public MultiAcceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        SocketChannel channel;
        try {
            channel = serverSocketChannel.accept();
            System.out.println(channel.getRemoteAddress()+":收到一个客户端连接");
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ,new MultipleDispathHandle(channel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
