package com.danssion.java.nettyExp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.nio.channels.ServerSocketChannel;

public class NettyBasicServerExample {

    /**
     * @param args
     * @desc 开发一个  主从多reactor 多线程模型的服务
     */
    public static void main(String[] args) {

        //主线程组
        EventLoopGroup mainGroup = new NioEventLoopGroup();
//        mainGroup.schedule();  定时任务
//        mainGroup.execute();   异步线程
//        mainGroup.register();   注册多路复用

        //表示多个工作线程组（默认cpu 核心数的两倍）
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        // 服务要启动，需要创建 ServerBootstrap,这里已经封装 NIO 的模板式的代码
        //构建 netty server API
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(mainGroup, workGroup)
                //指定 epoll 模型
                // 配置Server 通道，相当于 NIO 中ServerSocketChannel
//                .channel(EpollServerSocketChannel.class)
                .channel(NioServerSocketChannel.class)  //channel 表示采用什么样的通信方式 IO 方式
                //具体的工作处理类，负责处理相关SocketChannel的IO就绪事件
                // 设置 ServerSocketChannel 的handle
                .handler(new LoggingHandler(LogLevel.INFO))
                // childHandler 表示给 work 线程配置了一个处理器
                // 把处理业务的具体逻辑抽象出来 ，放到 handle 里面，处理
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 针对客户端 SocketChannel 设置处理链
                        ch.pipeline()
                                .addLast("h1",new NormalMessageHandle())//消息处理的 handle , 处理IO 事件
//                                .addLast()
                        //举例：
                        //.addLast 心跳的handler
                        //.addLast 编解码的、协议处理的、消息处理
                                //新增h2名字的  处理 handle
                                .addLast("h2", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("服务端 收到的数据：第二次处理");
                                        super.channelRead(ctx, msg);
                                    }
                                });
                    }
                });
        try {
            /**
             * 主要的启动逻辑  在  bind 方法中，需要完成以下事情：
             * 1. 初始化NioServerSocketChannel
             * 2. 初始化EventLoop
             * 3. NioServerSocketChannel 绑定监听端口
             * 4. NioServerSocketChannel 注册到 selector
             * 5. NioServerSocketChannel 中的 handler 构建
             * 6. NioSocketChannel 中的 handler 的构建
             */
            // bootstrap.bind(8090) 是异步，可以继续执行
            ChannelFuture channelFuture = bootstrap.bind(8090).sync(); //同步阻塞等到客户端连接
            System.out.println("Netty server started success !!");
            channelFuture.channel().closeFuture().sync();//同步等到服务端监听端口关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            mainGroup.shutdownGracefully();
        }
    }
}
