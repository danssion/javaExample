package com.danssion.java.nettyExp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

public class ByteBufCopyExample {

    public static void main(String[] args) {
        //提供 零拷贝机制 内存之间的数据拷贝

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        log(buf);
        //从 buf 中拆分
        //slice 使用的就是零拷贝机制 （浅克隆）
        ByteBuf bb1 = buf.slice(0, 5);
        ByteBuf bb2 = buf.slice(5, 5);
        log(bb1);
        log(bb2);
        System.out.println("修改原buf");
        buf.setByte(2, 8);
        log(bb1);
    }

    private static void log(ByteBuf buf) {
        StringBuilder sb = new StringBuilder();
        sb.append(" read Index:").append(buf.readerIndex()); //写索引
        sb.append(" write Index:").append(buf.writerIndex()); //读索引
        sb.append(" capacity：").append(buf.capacity()); //容量
        //16进制 美化的的 bytebuf 存入 sb 中
        ByteBufUtil.appendPrettyHexDump(sb, buf);
        System.out.println(sb.toString());
    }
}
