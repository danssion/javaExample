package com.danssion.java.nio.reactor;

import java.io.IOException;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/10/18 下午10:13
 * @desc JavaExample-DdsignPattern
 */
public class ReactorMain {
    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(8090), "main-thread").start();
    }
}
