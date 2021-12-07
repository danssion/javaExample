package com.danssion.java.nettyExp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

public class NettyByteBufCreateExample {
    public static void main(String[] args) {
        writeExp();
    }

    public static final void writeExp() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer();
        buf.writeBytes(new byte[]{1,2,3,4});//写四个字节
        log(buf);

        buf.writeInt(5);//占4个字节
        log(buf);
        System.out.println("开始读取操作");
        buf.markReaderIndex();//标记读取index ，可以实现数据重复读取.  markWriteIndex 同理
        byte b=buf.readByte();
        System.out.println(b);
        buf.resetReaderIndex();
        log(buf);
    }

    public static final void exp() {
        //堆内存，Java 虚拟机的内存，由JVM 管理
        ByteBufAllocator.DEFAULT.heapBuffer();


        //堆外内存，由系统管理，由自己操作管理。稍微快些
        ByteBufAllocator.DEFAULT.directBuffer();

        //ByteBuf 使用了（开启）内存池技术
        //不开启 池化技术
        //JVM: -Dio.netty.allocator.type={unpooled \ pooled}
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
