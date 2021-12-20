package com.danssion.java.nio.multiReactor;

import org.apache.ibatis.annotations.SelectKey;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Acceptor implements Runnable {
    final private Selector sel;


    final private ServerSocketChannel serverSocketChannel;

    private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private Reactor[] subReactors = new Reactor[POOL_SIZE];
    //Reactor 是独立线程处理，需要再定义一个线程池
    private Executor subReactorPool = Executors.newFixedThreadPool(POOL_SIZE);

    //Reactor 轮询初始化数
    int handlerNext = 0;

    public Acceptor(Selector sel, int port) throws IOException {
        this.sel = sel;
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(sel, SelectionKey.OP_ACCEPT, this);
        init();
        System.out.println("Main Reactor Acceptor Listen to port : " + port);
    }

    /**
     * 初始 线程池
     *
     * @throws IOException
     */
    private void init() throws IOException {
        for (int i = 0; i < subReactors.length; i++) {
            subReactors[i] = new Reactor();
            //用一个线程执行
            subReactorPool.execute(subReactors[i]);
        }
    }


    @Override
    public void run() {
        //用来处理 socket channel   连接事件  IO 事件
        try {
            SocketChannel socketChannel = this.serverSocketChannel.accept();//获取连接
            if (socketChannel != null) {
                socketChannel.write(ByteBuffer.wrap("multiple Reacotr Pat from Acceptor!\r\n reactor> ".getBytes()));
                System.out.println(Thread.currentThread().getName() + ": Main-Reactor-Acceptor:" + socketChannel.getLocalAddress());
                //已经获得连接，后续继续处理IO(由handle 继续处理)
                //分发给Reactor
                Reactor subReactor = this.subReactors[handlerNext];
                subReactor.register(new AsyncHandler(socketChannel));
                //轮询分发给 handle 处理
                if (++handlerNext == this.subReactors.length) {
                    //超过后就恢复 0
                    //简单的轮询算法
                    handlerNext = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
