package com.danssion.java.java8;

public interface Inter8 {
    void f();
    default void g() {
        System.out.println("this is default in interface");
    }

    static void h() {
        System.out.println("this is static method in interface");
    }
}
