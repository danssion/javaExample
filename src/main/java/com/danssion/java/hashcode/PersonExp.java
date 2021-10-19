package com.danssion.java.hashcode;

import java.util.HashMap;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/19 18:07
 * @desc
 */


class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 37 + age;
//        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.name.equals(((People) obj).name) && this.age == ((People) obj).age;
    }
}

public class PersonExp {

    public static void main(String[] args) {

        People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());

        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);

        System.out.println(hashMap.get(new People("Jack", 12))); // 1

        p1.setAge(13);

        System.out.println("hashcode 依赖于一个变量");
        System.out.println(hashMap.get(p1)); //null

    }

}
