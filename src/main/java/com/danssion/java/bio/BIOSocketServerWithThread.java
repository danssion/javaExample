package com.danssion.java.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/10 下午10:51
 * @desc JavaExample-DdsignPattern
 */
public class BIOSocketServerWithThread {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8090);
            System.out.println("启动服务，监听端口 8090");
            //重复处理
            while (true) {
                //阻塞等待接收一个客户端链接，返回socket 表示链接的客户端信息
                Socket socket = serverSocket.accept();//连接阻塞
                System.out.println("客户端：" + socket.getPort());
                //线程池处理 ，IO 变异步, 受限于 CPU 核心数
                executorService.submit(new SocketThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
