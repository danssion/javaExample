package com.danssion.java.reflect;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/4 20:18
 * @desc
 *
 * int.class和Integer.class不是同一份字节码，
 * Integer.TYPE，TYPE代表包装类对应的基本类的字节码 int.class==Integer.TYPE
 */

public class BaseInfo {
    public static void main(String[] args) {
//            info();
            getMethods();
//        newInstance();
//        testReflect();
//        contructor();
//        reflectField();
    }

    public static void info() {
        Person p1 = new Person();
        System.out.println(p1.getClass());
//下面的这三种方式都可以得到字节码
        Class c1 = Date.class;
        System.out.println(c1);

//若存在则加载，否则新建,往往使用第三种,类的名字在写源程序时不需要知道，到运行时再传递过来
        try {
            Class.forName("java.lang.String");
            System.out.println(Class.forName("java.lang.String"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getMethods() {
        try {
//            Class c =  Class.forName("org.apache.commons.lang3.StringUtils");
            Class c =  Class.forName("java.util.Stack");
            Method m[] = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++) {
                System.out.println(m[i].toString());
            }
            printClassName(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void newInstance() {
        Class c =  java.util.Stack.class;
        try {
            Object o = c.newInstance();
            System.out.println(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void testReflect() {
        // TODO Auto-generated method stub
        String str = "abc";
        Class cls1 = str.getClass();
        Class cls2 = String.class;
        Class cls3 = null;//必须要加上null
        try {
            cls3 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(" == ");
        System.out.println(cls1==cls2);
        System.out.println(cls1==cls3);

        System.out.println("  ");
        //isPrimitive(判断是否是基本类型的字节码)
        System.out.println(cls1.isPrimitive());
        System.out.println(int.class.isPrimitive());//判定指定的 Class 对象是否表示一个基本类型。
        System.out.println(" int.class ==  ");
        System.out.println(int.class == Integer.class);
        System.out.println(int.class == Integer.TYPE);
        System.out.println(int[].class.isPrimitive());
        System.out.println(int[].class.isArray());
    }

    public static void contructor() {
        String str = "abc";
        try {
            System.out.println(String.class.getConstructor(StringBuffer.class));
            Constructor<?> con[]  = str.getClass().getConstructors();
            System.out.println(con);
            for (int i = 0 ;i < con.length;i++) {
                System.out.println(con[i]);
                //print  all construct
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void printClassName(Object obj) {
        System.out.println("The class of " + obj +
                " is " + obj.getClass().getName());
    }


    /**
     * Filed类代表某一类中的一个成员变量
     */
    public static void reflectField() {
        ReflectPointer rp1 = new ReflectPointer(3,4);
        Field fieldx = null;//必须是x或者y
        try {
            fieldx = rp1.getClass().getField("x");
            System.out.println(fieldx.get(rp1));

            /*
             * private的成员变量必须使用getDeclaredField，并setAccessible(true),否则看得到拿不到
             */
            Field fieldy = rp1.getClass().getDeclaredField("y");
            fieldy.setAccessible(true);//暴力反射
            System.out.println(fieldy.get(rp1));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}




