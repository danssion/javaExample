package com.danssion.java.spring.configuration3;

import org.springframework.stereotype.Component;

@Component
public class TestBean3 {
    private String username;
    private String url;
    private String password;

    public void sayHello() {
        System.out.println("TestBean3 sayHello...");
    }

    public String toString() {
        return "TestBean3 username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }

    public void start() {
        System.out.println("TestBean3 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean3 销毁。。。");
    }
}
