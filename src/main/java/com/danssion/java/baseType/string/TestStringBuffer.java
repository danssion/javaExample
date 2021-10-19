package com.danssion.java.baseType.string;
import sun.rmi.runtime.Log;

public class TestStringBuffer {
    public static void testString() {
        String s="Hello";
        String s1=" World";
        long start = System.currentTimeMillis();
        for (int i = 0;i < 10000 ; i++) {
            s += s1;
        }
        long end = System.currentTimeMillis();
        long runtime = (end - start);
        System.out.println("testString:"+runtime);
    }

    public static void testStringBuffer() {
        StringBuffer s = new StringBuffer("Hello");
        String s1 = " World";
        long start = System.currentTimeMillis();
        for (int i = 0;i < 10000 ; i++) {
            s.append(s1);
        }
        long end = System.currentTimeMillis();
        long runtime = (end - start);
        System.out.println("testStringBuffer:"+runtime);
    }


    public static void main(String[] args) {
        testString();
        testStringBuffer();
    }
}
