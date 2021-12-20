/**
 * \* Created with IntelliJ IDEA.
 * \* Name: BootstrapTest
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 20:37
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.classload;

import java.net.URL;

public class BootstrapTest {
    public static void main(String[] args) {
        // 获取根类加载器所加载的全部URL数组
        URL[] urls = sun.misc.Launcher.
                getBootstrapClassPath().getURLs();
        // 遍历、输出根类加载器加载的全部URL
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
}
