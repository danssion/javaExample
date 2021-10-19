package com.danssion.java.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/12 下午10:35
 * @desc JavaExample-DdsignPattern
 */
public class Reactor implements Runnable {
    final private Selector selector;
    final private ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        //Object att  =  Acceptor
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor(selector,serverSocketChannel));

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    dispatch(iterator.next());
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey key) {
        //可能拿到的对象有两个  ：
        // Accepter
        //  Handler
        Runnable runnable = (Runnable) key.attachment();
        if(runnable != null) {
            //调用run 方法没唤醒新线程
            runnable.run();
        }
    }
}
