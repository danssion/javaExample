package com.danssion.java.nio.multiReactor;

import com.danssion.java.construct.Ex;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AsyncHandler implements Runnable {
    private SocketChannel channel;
    private SelectionKey sk;

    StringBuilder stringBuilder = new StringBuilder();

    ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
    ByteBuffer outputBuffer = ByteBuffer.allocate(1024);

    public AsyncHandler(SocketChannel channel) {
        this.channel = channel;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public SelectionKey getSk() {
        return sk;
    }

    public void setSk(SelectionKey sk) {
        this.sk = sk;
    }

    @Override
    public void run() {
        try {
            //判断事件类型
            if (sk.isReadable()) {
                this.read();
            } else if (sk.isWritable()) {
                this.write();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void read() throws IOException {
        //避免多个连接的影响
        inputBuffer.clear();
        int n = channel.read(inputBuffer);
        if (inputBufferCompleted(n)) { //如果读取完了
            System.out.println(Thread.currentThread().getName() + ": Server 收到请求消息： " + stringBuilder.toString());
            outputBuffer.put(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            this.sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private boolean inputBufferCompleted(int by) throws EOFException {
        if (by > 0) {
            inputBuffer.flip();
            //判断缓存区是否还有元素
            while (inputBuffer.hasRemaining()) {
                byte ch = inputBuffer.get();//得到输入的字符
                //连接结束
                //ASC 码：
                //3
                //ETX (end of text)
                //正文结束
                if (ch == 3) { // 表示 Ctrl + C
                    throw new EOFException();
                } else if (ch == '\r' || ch == '\n') { //是一个换行的情况下
                    return true;
                } else {
                    stringBuilder.append((char) ch);
                }
            }
        } else if (by == 1) {//客户端关闭连接
            throw new EOFException();
        }
        return false;
    }

    private void write() throws IOException {
        int write = -1;
        outputBuffer.flip();
        if (outputBuffer.hasRemaining()) {
            write = channel.write(outputBuffer);//收到的数据写回客户端
        }

        outputBuffer.clear();
        //清空数据
        stringBuilder.delete(0, stringBuilder.length());
        if (write <= 0) {
            this.sk.channel().close();
        } else {
            channel.write(ByteBuffer.wrap("\r\nreactor>".getBytes()));
            //写完后转换为 监听客户端读取的状态
            this.sk.interestOps(SelectionKey.OP_READ);
        }
    }
}
