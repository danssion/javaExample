package com.danssion.java.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/10 下午11:28
 * @desc JavaExample-DdsignPattern
 */
public class SocketThread implements Runnable {

    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            //input stream 阻塞的
            String clientStr = bufferedReader.readLine();
            System.out.println("收到客户端信息："+clientStr);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("receive a message:"+clientStr+"/n");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
