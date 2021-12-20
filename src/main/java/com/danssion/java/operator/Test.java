package com.danssion.java.operator;

public class Test {
    public static void main(String[] args) {
        andOperator();
    }

    public static void plusplus() {
        int i = 1;
        int j = 0;
        int k = 0;
        j = i++;
        k = ++i;
        System.out.println(j);
        System.out.println(k);
    }

    public static void moveSit() {
        int i = 1;
        System.out.println("1 << 1");
        System.out.println(i << 1);
        System.out.println("1 << 4");
        System.out.println(i << 4);
    }

    public static void andOperator() {
        int i = 1;
        int j = 2;
        int k = 5;
        System.out.println(i & k);
        System.out.println(k & j);
    }
}
