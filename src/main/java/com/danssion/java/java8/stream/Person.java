package com.danssion.java.java8.stream;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
class Person implements Comparable<Person> {
    private String name;
    private int age;
    private boolean isStudent = false;
    private int sex = 1;
    public Person(){}
    public Person(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public Person(String name,int age,boolean stu) {
        this.name = name;
        this.age = age;
        this.isStudent = stu;
    }
    public Person(String name,int age,int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static Person getInstance (final Supplier<Person> supplier) {
        return supplier.get();
    }

    public static int compareByAge(Person a, Person b) {
        return b.age - a.age;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age , o.getAge());
    }
}
