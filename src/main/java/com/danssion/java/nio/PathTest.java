/**
 * \* Created with IntelliJ IDEA.
 * \* Name: PathTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 19:59
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.nio;


import java.io.*;
import java.net.*;
import java.nio.file.*;

public class PathTest {
    public static void main(String[] args)
            throws Exception
    {
        // 以当前路径来创建Path对象
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量："  + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());
        // 获取path对应的绝对路径。
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        // 获取绝对路径的根路径
        System.out.println("absolutePath的根路径：" + absolutePath.getRoot());
        // 获取绝对路径所包含的路径数量
        System.out.println("absolutePath里包含的路径数量："
                + absolutePath.getNameCount());
        System.out.println("absolutePath.getName(3):"+ absolutePath.getName(3));
        System.out.println("absolutePath.getFileName:"+ absolutePath.getFileName());
        System.out.println("class path:"+PathTest.class.getClassLoader().getResource("").toString());
        System.out.println("class path2:"+PathTest.class.getClassLoader().getResource("").toURI().getRawPath());
        System.out.println("class path3:"+PathTest.class.getClass().getResource("").toString());


        // 以多个String来构建Path对象
        Path path2 = Paths.get("/Users/danssion" , "dev/source/app" , "git/JavaEE/SpringMVC");
        System.out.println(path2);
    }
}
