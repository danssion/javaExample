package com.danssion.java.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/10 下午10:51
 * @desc JavaExample-DdsignPattern
 */
public class BIOSocketServer {
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
                //input stream 阻塞的
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientStr = bufferedReader.readLine();
                System.out.println("收到客户端信息：" + clientStr);

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write("receive a message:" + clientStr + "/n");
                bufferedWriter.flush();
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
