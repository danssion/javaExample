/**
 * \* Created with IntelliJ IDEA.
 * \* Name: Apple2
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 16:01
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

public class Apple2 extends Apple {
    // 重写父类的方法
    public String getInfo()
    {
        // super.getInfo()方法返回值是Object类型，
        // 所以加toString()才返回String类型
        return super.getInfo().toString();
//        return super.getInfo();
    }

//    public Object getInfo() {
//        return super.getInfo();
//    }
}
