package com.danssion.java.reflect;

import java.util.function.Supplier;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/4 20:19
 * @desc
 */
class Person {
    private String name;
    private int age;
    public Person(){}
    public Person(String name,int age) {
        this.name = name;
        this.age = age;
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
