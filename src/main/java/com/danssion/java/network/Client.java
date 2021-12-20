/**
 * \* Created with IntelliJ IDEA.
 * \* Name: Client
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 11:56
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.network;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args)
            throws IOException {
        Socket socket = new Socket("127.0.0.1", 30000);   // ①

//        Socket sk = new Socket();
//        sk.connect(new InetSocketAddress("127.0.0.1",3000),1000);

        // 将Socket对应的输入流包装成BufferedReader
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        // 进行普通IO操作
        String line = br.readLine();
        System.out.println("来自服务器的数据：" + line);
        // 关闭输入流、socket
        br.close();
        socket.close();
    }


}
