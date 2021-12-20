/**
 * \* Created with IntelliJ IDEA.
 * \* Name: ClassLoaderTest
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 20:32
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.classload;

class Tester {
    static {
        System.out.println("Tester类的静态初始化块...");
    }
}

public class ClassLoaderTest {
    public static void main(String[] args)
            throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        // 下面语句仅仅是加载Tester类
        cl.loadClass("Tester");
        System.out.println("系统加载Tester类");
        // 下面语句才会初始化Tester类
        Class.forName("Tester");
    }
}

