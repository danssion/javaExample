package com.danssion.java.memory;

public class JVMMemory {
    public static void main(String[] args) {
        //JVM中空闲内存（字节）
        System.out.println(Runtime.getRuntime().freeMemory());
        //JVM内存总量
        System.out.println(Runtime.getRuntime().totalMemory());
        //JVM试图使用的最大内存
        System.out.println(Runtime.getRuntime().maxMemory());
        //可用的处理器数量
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
