package com.danssion.java.nettyExp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

public class NormalMessageHandle extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //定义数组,获取数据
        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);
        System.out.println("服务端 收到的数据：" + new String(req, "utf-8"));

        ByteBuf resp = Unpooled.copiedBuffer("Sever get msg!".getBytes());
        ctx.write(resp);
        super.channelRead(ctx, msg);
    }

    //读取完成
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //数据读取完成后 ，再把服务端数据写回客户端    listener 监听链接关闭事件
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        super.channelReadComplete(ctx);
    }

    //触发异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    /**
     * channel 的生命周期
     **/
    //channel 注册到 EvenLoop 上的时候，会触发的回调
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    //channel 未注册 或者 取消的时候
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    //channel 就绪，可以被读写的时候
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
