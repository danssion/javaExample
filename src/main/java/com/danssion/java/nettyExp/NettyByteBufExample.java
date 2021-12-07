package com.danssion.java.nettyExp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

public class NettyByteBufExample {
    public static void main(String[] args) {
        //构建一个 butebuf 实例
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println("----- before -----");
        log(buf);
        //构建一个字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            stringBuilder.append("-"+i);
        }
        //写入数据
        buf.writeBytes(stringBuilder.toString().getBytes());
        System.out.println("----- after -----");
        buf.readByte();//读取一个字节
        buf.readShort();//读取2个字节
        log(buf);
    }

    private static void log(ByteBuf buf) {
        StringBuilder sb = new StringBuilder();
        sb.append(" read Index:").append(buf.readerIndex()); //写索引
        sb.append(" write Index:").append(buf.writerIndex()); //读索引
        sb.append(" capacity：").append(buf.capacity()); //容量
        //16进制 美化的的 bytebuf 存入 sb 中
        ByteBufUtil.appendPrettyHexDump(sb,buf);
        System.out.println(sb.toString());
    }


}
