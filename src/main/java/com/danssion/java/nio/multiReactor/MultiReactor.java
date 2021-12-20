package com.danssion.java.nio.multiReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/12 下午10:35
 * @desc JavaExample-DdsignPattern
 */
public class MultiReactor {
    final private int port;

    private Reactor mainReactor;//接收io

    //线程池
    Executor mainReactorPool = Executors.newFixedThreadPool(5);

    public MultiReactor(int port) throws IOException {
        this.port = port;

        mainReactor = new Reactor();
    }

    private void run1() throws IOException {
        new Acceptor(this.mainReactor.getSelector(), this.port);
        //一个主力线程 不断接收链接
        mainReactorPool.execute(mainReactor);
    }

    public void start() throws IOException {
        new Acceptor(mainReactor.getSelector(), port);
        mainReactorPool.execute(mainReactor);
    }

    public static void main(String[] args) throws IOException {
        new MultiReactor(8090).start();
    }

}
