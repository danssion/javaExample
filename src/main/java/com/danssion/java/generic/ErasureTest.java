/**
 * \* Created with IntelliJ IDEA.
 * \* Name: ErasureTest
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 17:42
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

import java.util.ArrayList;
import java.util.List;

class AppleT<T extends Number> {
    T size;

    public AppleT() {
    }

    public AppleT(T size) {
        this.size = size;
    }

    public void setSize(T size) {
        this.size = size;
    }

    public T getSize() {
        return this.size;
    }
}

public class ErasureTest {
    public static void main(String[] args) {
        AppleT<Integer> a = new AppleT<>(6);    // ①
        // a的getSize方法返回Integer对象
        Integer as = a.getSize();
        // 把a对象赋给Apple变量，丢失尖括号里的类型信息
        AppleT b = a;      // ②
        // b只知道size的类型是Number
        Number size1 = b.getSize();
        // 下面代码引起编译错误
//        Integer size2 = b.getSize();  // ③

        ErasureTest.main2();
    }


    public static void main2() {
        List<Integer> li = new ArrayList<>();
        li.add(6);
        li.add(9);
        List list = li;
        // 下面代码引起“未经检查的转换”的警告，编译、运行时完全正常
        List<String> ls = list;     // ①
        // 但只要访问ls里的元素，如下面代码将引起运行时异常。
//        System.out.println(ls.get(0));
        System.out.println(ls.get(0).toString());
        System.out.println((String) ls.get(0));
    }
}
