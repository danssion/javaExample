package com.danssion.java.java7;

import org.apache.commons.math3.exception.NumberIsTooLargeException;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;

public class Hello7 {

    public static void main(String[] args) {
        Hello7 hello7 = new Hello7();
        hello7.numDefine();

        ByteBufferUsage bufferUsage = new ByteBufferUsage();
        bufferUsage.testBuffer();
    }

    /**
     * 数字字面量的改进 / 数值可加下划
     */
    private void numDefine() {
        byte abyte = 0B0001;
        byte bbyte = 0b1010;
        short ashort = 0b100001011010;
        short bshort = 0b100001011010;

        long aint = 123_456_789_000L;
        long bint = 100_00_0_0_0_0_000L;

        System.out.println(abyte | bbyte);
        System.out.println(ashort & bshort);
        System.out.println(aint + " " + bint);
    }


    /**
     * 异常处理（捕获多个异常）
     * try-with-resources语句
     * Java7中在try语句中申请资源，实现资源的自己主动释放（资源类必须实现java.lang.AutoCloseable接口，
     * 一般的文件、数据库连接等均已实现该接口，close方法将被自己主动调用）
     */
    public String read(String filename) throws IOException {
        try {
            Integer.parseInt("Hello");
        } catch (NumberFormatException | NumberIsTooLargeException e) {  //使用'|'切割，多个类型，一个对象e

        }


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(String.format("%n"));
            }
            return builder.toString();
        }

    }


    /**
     * NIO2.0（AIO）新IO的支持
     * bytebuffer
     */
    static class ByteBufferUsage {

        public static void useByteBuffer() {
            ByteBuffer buffer = ByteBuffer.allocate(32);
            buffer.put((byte) 1);
            buffer.put(new byte[3]);
            buffer.putChar('A');
            buffer.putFloat(0.0f);
            buffer.putLong(10, 100L);
            System.out.println(buffer.getChar(4));
            System.out.println("buffer remain:" + buffer.remaining());
            System.out.println(buffer.getLong());
            System.out.println("buffer remain:" + buffer.remaining());
        }

        public static void byteOrder() {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putInt(1);
            buffer.putInt(2);
            System.out.println("byteOrder ------ ");
            System.out.println("buffer remain:" + buffer.remaining());
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            System.out.println("after ByteOrder.LITTLE_ENDIAN remain:" + buffer.remaining());
            System.out.println(buffer.getInt(0));
            ; //值为16777216
        }

        public static void compact() {
            ByteBuffer buffer = ByteBuffer.allocate(32);
            buffer.put(new byte[16]);
            buffer.flip();
            buffer.getInt();
            buffer.compact();
            int pos = buffer.position();
            System.out.println("compact ------ ");
            System.out.println("buffer remain:" + buffer.remaining());
            System.out.println(pos);
        }

        public static void viewBuffer() {
            ByteBuffer buffer = ByteBuffer.allocate(32);
            System.out.println("viewBuffer -----");
            System.out.println("buffer remain:" + buffer.remaining());
            buffer.putInt(1);
            IntBuffer intBuffer = buffer.asIntBuffer();
            intBuffer.put(2);
            System.out.println("buffer remain:" + buffer.remaining());
            int value = buffer.getInt(); //值为2
            System.out.println(value);
        }

        public static void testBuffer() {
            useByteBuffer();
            byteOrder();
            compact();
            viewBuffer();
        }


        public static void openAndWrite() throws IOException {
            FileChannel channel = FileChannel.open(Paths.get("myPromotion.txt"),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(64);
            buffer.putChar('A').flip();
            channel.write(buffer);
        }

        public static void readWriteAbsolute() throws IOException {
            FileChannel channel = FileChannel.open(Paths.get("absolute.txt"),
                    StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            ByteBuffer writeBuffer = ByteBuffer.allocate(4).putChar('A').putChar('B');
            writeBuffer.flip();
            channel.write(writeBuffer, 1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(2);
            channel.read(readBuffer, 1026);
            readBuffer.flip();
            char result = readBuffer.getChar(); //值为'B'
        }

    }


}

