package com.danssion.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/9 17:44
 * @desc
 */

class Ss {

}

public class ExampleReflect {
    public static void main(String[] args) {
//        updateClassParamStringBtoA();

//        instanceofLike();

        useMethod();
    }


    public static void updateClassParamStringBtoA() {
        ReflectPointer rp1 = new ReflectPointer(3, 4);
        Field[] fields = rp1.getClass().getFields();
        System.out.println("old:" + rp1);
        try {
            for (Field field : fields) {
//                System.out.println(field.getType());
//                System.out.println(field.getType().equals(String.class));
                //if(field.getType().equals(String.class))
                //由于字节码只有一份,用equals语义不准确
                if (field.getType() == String.class) {
                    String oldValue = null;
                    oldValue = (String) field.get(rp1);
                    String newValue = oldValue.replace('b', 'a');
                    field.set(rp1, newValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("new : " + rp1);
    }

    public static void instanceofLike() {
        try {
            Class cls = Class.forName("com.danssion.java.reflect.Ss");
            boolean b1 = cls.isInstance(new Integer(37));
            System.out.println(b1);
            boolean b2 = cls.isInstance(new Ss());
            System.out.println(b2);
        } catch (Throwable e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }


    /**
     * 代表类（不是对象）中的某一方法。
     */
    public static void useMethod() {
        String str = "shfsfs";
        //包开头是com表示是sun内部用的，java打头的才是用户的
        Method mtCharAt = null;
        try {
            mtCharAt = String.class.getMethod("charAt", int.class);
            Object ch = mtCharAt.invoke(str, 1);//若第一个参数是null，则肯定是静态方法
            System.out.println(ch);

            System.out.println(mtCharAt.invoke(str, new Object[]{2})); //1.4语法

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}


