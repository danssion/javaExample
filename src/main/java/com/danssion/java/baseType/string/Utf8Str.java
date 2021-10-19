package com.danssion.java.baseType.string;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/7/15 下午5:40
 * @desc JavaExample-DdsignPattern
 */
public class Utf8Str {
    public static void main(String[] args) {
        testUtf8mb4();
    }

    public static void testUtf8mb4() {
        String str = "abcd";
        String ustr = "😀🍒🏸📚";

        System.out.println(str +" length: "+str.length());
        System.out.println(str +" codeP: "+str.codePointCount(0,str.length()));

        System.out.println(ustr +" length: "+ustr.length());
        System.out.println(ustr +" codeP: "+ustr.codePointCount(0,ustr.length()));
    }
}
