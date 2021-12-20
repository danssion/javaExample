package com.danssion.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/11 下午10:49
 * @desc JavaExample-DdsignPattern
 */
public class NIOSelectorSocketServer implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public NIOSelectorSocketServer(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        //采用 selector 模型必须采用非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //连接就绪，再获取IO
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public NIOSelectorSocketServer(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        //线程没终止时
        while (!Thread.interrupted()) {
            try {
                //阻塞等待事件就绪
                selector.select();
                //获取所有就绪的事件列表，
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    //有连接ok
                    this.dispatch((SelectionKey) it.next());
                    it.remove();//移除当前就绪事件，否则重复监听
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey key) throws IOException {
        if (key.isAcceptable()) { //可以用事件
            this.register(key);
        } else if (key.isReadable()) { //读取事件
            this.read(key);
        } else if (key.isWritable()) { //写事件
            //todo
        }
    }

    private void register(SelectionKey key) throws IOException {
        //获取服务端
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        //获得客户端链接，（一定有连接）
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        //再次注册，channel 为Read 事件
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        //得到一个 socket channal
        SocketChannel channel = (SocketChannel) key.channel();
        //申请缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        System.out.println("server Recieve msg:" + new String(buffer.array()));
    }

    public static void main(String[] args) throws IOException {
        NIOSelectorSocketServer selectorSocketServer = new NIOSelectorSocketServer(8090);
        new Thread(selectorSocketServer).start();
    }
}
