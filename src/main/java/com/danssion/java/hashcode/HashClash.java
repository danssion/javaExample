package com.danssion.java.hashcode;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/19 19:37
 * @desc
 */
public class HashClash {

    public static void main(String[] args) {
        sameHash();
    }

    public static void sameHash() {
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBAaBBBBAa".hashCode());
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBAaBBBBBB".hashCode());
// 输出
//        2112
//        2112
//        2067858432
//        2067858432
    }
}
