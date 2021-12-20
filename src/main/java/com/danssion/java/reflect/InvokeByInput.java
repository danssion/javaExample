package com.danssion.java.reflect;

import java.lang.reflect.Method;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/9 19:25
 * @desc
 */
public class InvokeByInput {
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, Exception {
        String str = args[0];
        /*
         * 这样会数组角标越界，因为压根没有这个字符数组
         * 需要右键在run as-configurations-arguments里输入b.Inter（完整类名）
         *
         */
        Method m = Class.forName(str).getMethod("main", String[].class);
        //下面这两种方式都可以,main方法需要一个参数

        m.invoke(null, new Object[]{new String[]{"111", "222", "333"}});
        m.invoke(null, (Object) new String[]{"111", "222", "333"});//这个可以说明，数组也是Object
        /*
         * m.invoke(null, new String[]{"111","222","333"})
         * 上面的不可以,因为java会自动拆包
         */
    }
}

class Inter {
    public static void main(String[] args) {
        for (Object obj : args) {
            System.out.println(obj);
        }
    }
}
