package com.danssion.java.nettyExp.packet;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

public class NettyPackageClient {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                //表示传输消息的时候，在消息报文中增加4个字节的length -> 发送的 ByteBuf 中
                                //报文 ：
                                // length(4 字节) + Content
                                .addLast(new LengthFieldPrepender(4, 0, false))
                                .addLast(new StringEncoder())
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        ctx.writeAndFlush("i am first request");
                                        ctx.writeAndFlush("i am second request");
                                    }
                                });
                        //其他列子
//                                .addLast(new FixedLengthFrameDecoder(36)) //UUID length 36 使用长度解码器
//                                .addLast(new SimpleClientHandle());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect("localhost", 8090).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
