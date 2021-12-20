/**
 * \* Created with IntelliJ IDEA.
 * \* Name: AppleT
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 15:54
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

// 定义Apple类时使用了泛型声明
public class Apple<T> {
    // 使用T类型形参定义实例变量
    private T info;

    public Apple() {
    }

    // 下面方法中使用T类型形参来定义构造器
    public Apple(T info) {
        this.info = info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return this.info;
    }

    public static void main(String[] args) {
        // 由于传给T形参的是String，所以构造器参数只能是String
        Apple<String> a1 = new Apple<>("苹果");
        System.out.println(a1.getInfo());
        // 由于传给T形参的是Double，所以构造器参数只能是Double或double
        Apple<Double> a2 = new Apple<>(5.67);
        System.out.println(a2.getInfo());
    }
}
