package com.danssion.java.nettyExp.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

public class SimpleSeverHandle extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf in = (ByteBuf)msg;
//        //定义数组,获取数据
//        byte[] req = new byte[in.readableBytes()];
//        in.readBytes(req);
//        String body = new String(req,"UTF-8");
//        System.out.println("服务端 收到的数据："+body);
        System.out.println(msg);
        //写回数据
//        ByteBuf resp = Unpooled.copiedBuffer(("Receive msg: "+body+" ").getBytes());
//        ByteBuf resp = Unpooled.copiedBuffer(UUID.randomUUID().toString().getBytes());
//        ctx.writeAndFlush(resp);
//        super.channelRead(ctx, msg);
    }
}
