package com.danssion.java.nettyExp;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;

public class EventLoopExample {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup(2);
//        group.register();//把某个channel 注册到 某一个EventLoop 的selector 上   单独的引擎，事件循环机制

        //每次循环都会轮询获取某个 group
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        //获取 loop,注册channel
        NioEventLoop eventExecutors = (NioEventLoop)group.next();

        //向group 提交任务
        group.next().submit(() -> {
            System.out.println(Thread.currentThread().getName()+"----");
        });
    }
}
