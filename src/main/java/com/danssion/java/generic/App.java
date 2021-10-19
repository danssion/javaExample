/**
 * \* Created with IntelliJ IDEA.
 * \* Name: App
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 16:28
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

public class App<T extends Number>
{
    T col;
    public static void main(String[] args)
    {
        App<Integer> ai = new App<>();
        App<Double> ad = new App<>();
        // 下面代码将引起编译异常，下面代码试图把String类型传给T形参
        // 但String不是Number的子类型，所以引发编译错误
//        App<String> as = new App<>();		// ①
    }
}
