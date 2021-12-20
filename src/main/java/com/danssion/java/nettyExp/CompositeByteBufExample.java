package com.danssion.java.nettyExp;

import io.netty.buffer.*;

public class CompositeByteBufExample {
    public static void main(String[] args) {
        ByteBuf h = ByteBufAllocator.DEFAULT.buffer();
        h.writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBuf body = ByteBufAllocator.DEFAULT.buffer();
        body.writeBytes(new byte[]{6, 7, 8, 9, 10});
        //一般 操作
//        ByteBuf total = Unpooled.buffer(h.readableBytes()+body.readableBytes());
//        total.writeBytes(h);
//        total.writeBytes(body);

        //从逻辑层面构建了一个总的buf  ，都是引用的
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(true, h, body);
        log(compositeByteBuf);

        //unpooled  依旧是零拷贝
        ByteBuf up = Unpooled.wrappedBuffer(h, body);
        log(up);
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
