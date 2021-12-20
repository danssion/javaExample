/**
 * \* Created with IntelliJ IDEA.
 * \* Name: Apple1
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 15:55
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

public class Apple1 extends Apple<String> {
    // 正确重写了父类的方法，返回值
    // 与父类Apple<String>的返回值完全相同
    public String getInfo() {
        return "子类" + super.getInfo();
    }

    // 下面方法是错误的，重写父类方法时返回值类型不一致
//	public Object getInfo()
//	{
//		return "子类";
//	}


}
