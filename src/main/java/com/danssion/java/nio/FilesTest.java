/**
 * \* Created with IntelliJ IDEA.
 * \* Name: FilesTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 20:02
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.nio;


import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.util.*;


public class FilesTest {

    public static void main(String[] args)
            throws Exception
    {
        // 复制文件
        String fileName = "conditionFile.txt";
        Files.copy(Paths.get(fileName)
                , new FileOutputStream("a.txt"));
        // 判断FilesTest.java文件是否为隐藏文件
        System.out.println(fileName+"是否为隐藏文件："
                + Files.isHidden(Paths.get(fileName)));
        // 一次性读取FilesTest.java文件的所有行
        List<String> lines = Files.readAllLines(Paths
                .get(fileName), Charset.forName("gbk"));
        System.out.println(lines);
        // 判断指定文件的大小
        System.out.println(fileName+"的大小为："
                + Files.size(Paths.get(fileName)));
        List<String> poem = new ArrayList<>();
        poem.add("水晶潭底银鱼跃");
        poem.add("清徐风中碧竿横");
        // 直接将多个字符串内容写入指定文件中
        Files.write(Paths.get("pome.txt") , poem
                , Charset.forName("utf-8"));
        // 使用Java 8新增的Stream API列出当前目录下所有文件和子目录
        Files.list(Paths.get(".")).forEach(path -> System.out.println(path));
        // 使用Java 8新增的Stream API读取文件内容
        Files.lines(Paths.get(fileName) , Charset.forName("utf-8"))
                .forEach(line -> System.out.println(line));
        FileStore cStore = Files.getFileStore(Paths.get("/Library/Java"));
        // 判断C盘的总空间，可用空间
        System.out.println("C:共有空间：" + cStore.getTotalSpace());
        System.out.println("C:可用空间：" + cStore.getUsableSpace());
    }
}
