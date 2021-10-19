/**
 * \* Created with IntelliJ IDEA.
 * \* Name: R
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 16:17
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

public class R<T>
{
    // 下面代码错误，不能在静态变量声明中使用类型形参
//	static T info;
    T age;
    public void foo(T msg){}
    // 下面代码错误，不能在静态方法声明中使用类型形参
//	public static void bar(T msg){}
//	public static void b(? msg){}

}


