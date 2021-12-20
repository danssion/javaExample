/**
 * \* Created with IntelliJ IDEA.
 * \* Name: GenericConstructor
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 17:16
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;


class Foo {
    public <T> Foo(T t) {
        System.out.println(t);
    }
}

public class GenericConstructor {
    public static void main(String[] args) {
        // 泛型构造器中的T参数为String。
        new Foo("疯狂Java讲义");
        // 泛型构造器中的T参数为Integer。
        new Foo(200);
        // 显式指定泛型构造器中的T参数为String，
        // 传给Foo构造器的实参也是String对象，完全正确。
        new <String>Foo("疯狂Android讲义");
        // 显式指定泛型构造器中的T参数为String，
        // 但传给Foo构造器的实参是Double对象，下面代码出错
//        new <String> Foo(12.3);
    }
}

