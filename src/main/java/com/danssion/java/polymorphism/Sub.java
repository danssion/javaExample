/**
 * \* Created with IntelliJ IDEA.
 * \* Name: Sub
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 11:47
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.polymorphism;

public class Sub extends Base {
    //重新定义一个book实例变量隐藏父类的book实例变量
    public String book = "轻量级Java EE企业应用实战";

    public void test() {
        System.out.println("子类的覆盖父类的方法");
    }

    public void sub() {
        System.out.println("子类的普通方法");
    }

    public static void main(String[] args) {
        // 下面编译时类型和运行时类型完全一样，因此不存在多态
        Base bc = new Base();
        // 输出 6
        System.out.println(bc.book);
        // 下面两次调用将执行BaseClass的方法
        bc.base();
        bc.test();
        // 下面编译时类型和运行时类型完全一样，因此不存在多态
        Sub sc = new Sub();
        // 输出"轻量级Java EE企业应用实战"
        System.out.println(sc.book);
        // 下面调用将执行从父类继承到的base()方法
        sc.base();
        // 下面调用将执行从当前类的test()方法
        sc.test();


        // 下面编译时类型和运行时类型不一样，多态发生
        Base ploymophicBc = new Sub();
        // 输出6 ―― 表明访问的是父类对象的实例变量
        System.out.println(ploymophicBc.book);
        // 下面调用将执行从父类继承到的base()方法
        ploymophicBc.base();
        // 下面调用将执行从当前类的test()方法
        ploymophicBc.test();
        // 因为ploymophicBc的编译类型是BaseClass，
        // BaseClass类没有提供sub方法,所以下面代码编译时会出现错误。
        // ploymophicBc.sub();
    }
}
