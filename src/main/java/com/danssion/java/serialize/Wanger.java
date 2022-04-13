package com.danssion.java.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/14 00:51
 * @desc 1）添加一个默认版本的序列化 ID：
 * private static final long serialVersionUID = 1L。
 * <p>
 * 2）添加一个随机生成的不重复的序列化 ID。
 * private static final long serialVersionUID = -2095916884810199532L;
 * <p>
 * 3）添加 @SuppressWarnings 注解。
 * @SuppressWarnings("serial")
 */
public class Wanger implements Serializable {
    private String name;
    private int age;

    public static String pre = "沉默";
    transient String meizi = "王三";

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

    /**
     * 可以通过下面的方法 重写 transient 修饰的属性
     * @return
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(meizi);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.meizi = (String)in.readObject();
    }

    @Override
    public String toString() {
        return "Wanger{" +
                "name='" + name + '\'' +
                ", age=" + age + ",pre=" + pre +
                ", meizi='" + meizi + '\'' +
                '}';
    }
}
