package com.danssion.java.nettyExp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyBasicServerExample {

    /**
     * @desc 开发一个  主从多reactor 多线程模型的服务
     * @param args
     */
    public static void main(String[] args) {

        //主线程组
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        //表示多个工作线程组（默认cpu 核心数的两倍）
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        //构建 netty server API
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(mainGroup,workGroup)
                //指定 epoll 模型
//                .channel(EpollServerSocketChannel.class)
                .channel(NioServerSocketChannel.class)  //channel 表示采用什么样的通信方式 IO 方式
                //具体的工作处理类，负责处理相关SocketChannel的IO就绪事件
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("h1",new NormalMessageHandle())//消息处理的 handle , 处理IO 事件
                        //举例：
                        //.addLast 心跳的handler
                        //.addLast 编解码的、协议处理的、消息处理
                                //新增h2名字的  处理 handle
                    .addLast("h2",new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println("服务端 收到的数据：第二次处理");
                            super.channelRead(ctx, msg);
                        }
                    });
                    }
                });
        try {
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
