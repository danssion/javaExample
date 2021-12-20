package com.danssion.java.baseType.enumType;

import java.util.Arrays;

public class EnumDemo {

    public static void main(String[] args) {

        //创建枚举数组
        Day[] days = new Day[]{Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY,
                Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};

        for (int i = 0; i < days.length; i++) {
            System.out.println("day[" + i + "].ordinal():" + days[i].ordinal());
        }

        System.out.println("-------------------------------------");
        //通过compareTo方法比较,实际上其内部是通过ordinal()值比较的
        System.out.println("days[0].compareTo(days[1]):" + days[0].compareTo(days[1]));
        System.out.println("days[0].compareTo(days[1]):" + days[0].compareTo(days[2]));

        //获取该枚举对象的Class对象引用,当然也可以通过getClass方法
        Class<?> clazz = days[0].getDeclaringClass();
        System.out.println("clazz:" + clazz);

        System.out.println("-------------------------------------");

        //name()
        System.out.println("days[0].name():" + days[0].name());
        System.out.println("days[1].name():" + days[1].name());
        System.out.println("days[2].name():" + days[2].name());
        System.out.println("days[3].name():" + days[3].name());

        System.out.println("-------------------------------------");

        System.out.println("days[0].toString():" + days[0].toString());
        System.out.println("days[1].toString():" + days[1].toString());
        System.out.println("days[2].toString():" + days[2].toString());
        System.out.println("days[3].toString():" + days[3].toString());

        System.out.println("-------------------------------------");

        Day d = Enum.valueOf(Day.class, days[0].name());
        Day d2 = Day.valueOf(Day.class, days[0].name());
        System.out.println("d:" + d);
        System.out.println("d2:" + d2);

        System.out.println("-------------------------------------");

        Day[] days2 = Day.values();
        System.out.println("day2:" + Arrays.toString(days2));
        Day day = Day.valueOf("MONDAY");
        System.out.println("day:" + day);

        System.out.println("-------------------------------------");

        //枚举与Class对象

        //正常使用
        Day[] ds = Day.values();
//向上转型Enum
        Enum e = Day.MONDAY;
//无法调用,没有此方法
//e.values();
//获取class对象引用
        Class<?> clasz = e.getDeclaringClass();
        if (clasz.isEnum()) {
            Day[] dsz = (Day[]) clasz.getEnumConstants();
            System.out.println("dsz:" + Arrays.toString(dsz));
        }


    }
    /**
     执行结果:
     day[0].ordinal():0
     day[1].ordinal():1
     day[2].ordinal():2
     day[3].ordinal():3
     day[4].ordinal():4
     day[5].ordinal():5
     day[6].ordinal():6
     -------------------------------------
     days[0].compareTo(days[1]):-1
     days[0].compareTo(days[1]):-2
     clazz:class com.zejian.enumdemo.Day
     -------------------------------------
     days[0].name():MONDAY
     days[1].name():TUESDAY
     days[2].name():WEDNESDAY
     days[3].name():THURSDAY
     -------------------------------------
     days[0].toString():MONDAY
     days[1].toString():TUESDAY
     days[2].toString():WEDNESDAY
     days[3].toString():THURSDAY
     -------------------------------------
     d:MONDAY
     d2:MONDAY
     -------------------------------------
     day2:[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]
     day:MONDAY
     -------------------------------------
     dsz:[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]

     */


}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

