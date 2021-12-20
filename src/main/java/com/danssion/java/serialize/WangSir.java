package com.danssion.java.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/14 01:25
 * @desc
 */
public class WangSir implements Externalizable {
    private String name;
    private int age;

    public static String pre = "沉默";
    transient String meizi = "王三";

    /**
     * 使用 Externalizable 进行反序列化的时候，会调用被序列化类的无参构造方法去创建一个新的对象，
     * 然后再将被保存对象的字段值复制过去。
     */
    public WangSir() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Wanger{" + "name=" + name + ",age=" + age + "}";
    }

    /**
     * 1）调用 ObjectOutput 的 writeObject() 方法将字符串类型的 name 写入到输出流中；
     * <p>
     * 2）调用 ObjectOutput 的 writeInt() 方法将整型的 age 写入到输出流中；
     * <p>
     * 3）调用 ObjectInput 的 readObject() 方法将字符串类型的 name 读入到输入流中；
     * <p>
     * 4）调用 ObjectInput 的 readInt() 方法将字符串类型的 age 读入到输入流中；
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }
}
