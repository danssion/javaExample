/**
 * \* Created with IntelliJ IDEA.
 * \* Name: testApp
 * \* User: danssion
 * \* Date: 2019/9/24
 * \* Time: 18:48
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.math;

public class testApp {

    public static void main(String[] args) {
        Double length  = new Double(111);

        Double a = new Double(length / 10);
        Integer threadNum = new Double(Math.ceil(a)).intValue();
        System.out.println((a));
        System.out.println(threadNum);
    }
}
