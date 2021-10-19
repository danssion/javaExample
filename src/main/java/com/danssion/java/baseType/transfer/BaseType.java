/**
 * \* Created with IntelliJ IDEA.
 * \* Name: BaseType
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 14:14
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.baseType.transfer;

public class BaseType {

    public static void main(String[] args) {
        //自动类型转换
        //char -> int -> long -> float -> double
        //byte -> short ->int ...

        int a  = 6;
        // int可以自动转换为float类型
        float f = a;
        // 下面将输出6.0
        System.out.println(f);
        // 定义一个byte类型的整数变量
        byte b = 9;
        // 下面代码将出错，byte型不能自动类型转换为char型
        // char c = b;
        // 下面是byte型变量可以自动类型转换为double型
        short s = b;
        System.out.println(b);
        double d = b;
        // 下面将输出9.0
        System.out.println(d);


        // 下面语句输出7Hello!
        System.out.println(3 + 4 + "Hello！");
        // 下面语句输出Hello!34，因为Hello! + 3会把3当成字符串处理，
        // 而后再把4当成字符串处理
        System.out.println("Hello！" + 3 + 4);
    }


    private void narrowConversion() {
        int iValue = 233;
        // 强制把一个int类型的值转换为byte类型的值
        byte bValue = (byte)iValue;
        // 将输出-23
        System.out.println(bValue);
        double dValue = 3.98;
        // 强制把一个double类型的值转换为int
        int tol = (int)dValue;
        // 将输出3
        System.out.println(tol);
    }
}
