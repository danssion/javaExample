package com.danssion.java.serialize;

import java.io.*;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/14 00:52
 * @desc
 */
public class TestSerialize {

    public static void main(String[] args) {
//        writeObject();

        extralizable();
    }

    public static void writeObject() {
        // 初始化
        Wanger wanger = new Wanger();
        wanger.setName("王二");
        wanger.setAge(18);
        System.out.println(wanger);

        // 把对象写到文件中
        //以ObjectOutputStream 为例吧，它在序列化的时候会依次调用
        //writeObject()→writeObject0() → writeOrdinaryObject() → writeSerialData()
        // → invokeWriteObject() → defaultWriteFields()
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ObjectFile"));) {
            oos.writeObject(wanger);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Wanger.pre = "不沉默";
        // 从文件中读出对象
        // 反序列化
        //  readObject()→readObject0()→readOrdinaryObject()→readSerialData()→defaultReadFields()
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("ObjectFile")));) {
            Wanger wanger1 = (Wanger) ois.readObject();
            System.out.println(wanger1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 1）序列化前，pre 的值为“沉默”，序列化后，pre 的值修改为“不沉默”，反序列化后，pre 的值为“不沉默”，而不是序列化前的状态“沉默”。
         * 2）序列化前，meizi 的值为“王三”，反序列化后，meizi 的值为 null，而不是序列化前的状态“王三”。
         *
         * transient 的中文字义为“临时的” 可以阻止字段被序列化到文件中，在被反序列化后，transient 字段的值被设为初始值，
         * 比如 int 型的初始值为 0，对象型的初始值为 null。
         */
    }

    public static void extralizable() {
        // 初始化
        WangSir wanger = new WangSir();
        wanger.setName("王二");
        wanger.setAge(18);
        System.out.println(wanger);

// 把对象写到文件中
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("chenmo"));) {
            oos.writeObject(wanger);
        } catch (IOException e) {
            e.printStackTrace();
        }

// 从文件中读出对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("chenmo")));) {
            WangSir wanger1 = (WangSir) ois.readObject();
            System.out.println(wanger1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
// Wanger{name=王二,age=18}
// Wanger{name=null,age=0}
    }
}
