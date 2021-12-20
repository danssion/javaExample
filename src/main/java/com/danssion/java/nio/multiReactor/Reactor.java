package com.danssion.java.nio.multiReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Reactor implements Runnable {
    private final Selector selector;

    //并发安全的队列
    private ConcurrentLinkedDeque<AsyncHandler> event = new ConcurrentLinkedDeque<>();


    public Reactor() throws IOException {
        this.selector = Selector.open();
    }

    public Selector getSelector() {
        return selector;
    }

    /**
     * 需要不断监听链接
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                //完成一个事件注册过程，区分事件是 读、写
                AsyncHandler handler;
                while ((handler = event.poll()) != null) { //阻塞队列，生产者 -> 消费者模式
                    //在 Acceptor 中注册到队列里
                    handler.getChannel().configureBlocking(false);
                    SelectionKey selectionKey = handler.getChannel().register(selector, SelectionKey.OP_READ);
                    //附件传入 handle ，由handle 处理后续的io
                    selectionKey.attach(handler);
                    handler.setSk(selectionKey);
                }
                //这里阻塞
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    Runnable runnable = (Runnable) key.attachment();//得到 Acceptor
                    if (runnable != null) {
                        runnable.run();
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param handler
     * @desc 把接收到的任务 扔到缓存队列，后续异步调用处理
     */
    public void register(AsyncHandler handler) {
        event.offer(handler);//有一个事件注册
        //唤醒 selector.select()阻塞
        selector.wakeup();
    }

}
