package com.danssion.java.java8.referenceFunction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.comparator.CompoundComparator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;

public class TestReference {

    public static void main(String[] args) {
        //引用构造方法
        Person p1 = Person.getInstance(Person::new);
        p1.setAge(19);
        System.out.println("测试引用构造方法： "+p1.getAge());

        Person[] people = {new Person("James",25),new Person("Jack",21)};
        //引用特定类的任意对象方法
        Arrays.sort(people, Comparator.comparing(Person::getAge));
        System.out.println("测试特定类的任意对象方法");
        for(Person p : people) {
            System.out.println(p);
        }

        //引用类静态方法
        Arrays.sort(people,Person::compareByAge);
        System.out.println("测试引用类的静态方法");
        for(Person p : people) {
            System.out.println(p);
        }

        //引用某个对象的方法
        Arrays.sort(people,new CompareProvide()::compareByAge);
        System.out.println("测试引用某个对象的方法");
        for(Person p : people) {
            System.out.println(p);
        }
    }
}

@Getter
@Setter
class Person {
    private String name;
    private int age;
    public Person(){}
    public Person(String name,int age) {
        this.name = name;
        this.age = age;
    }

    public static Person getInstance (final Supplier<Person> supplier) {
        return supplier.get();
    }

    public static int compareByAge(Person a,Person b) {
        return b.age - a.age;
    }

    public String toString() {
        return name + ":" + age;
    }
}


class CompareProvide {
    public int compareByAge(Person a,Person b) {
        return a.getAge() - b.getAge();
    }
}