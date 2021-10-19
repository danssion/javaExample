/**
 * \* Created with IntelliJ IDEA.
 * \* Name: BaseType
 * \* User: danssion
 * \* Date: 2020/2/1
 * \* Time: 19:34
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.baseType;

import javax.sound.midi.Soundbank;

public class BaseType {
    public static void main(String[] args) {
//        doubleVsLong();
//        intToDouble();
        byteCompare();
    }

    public static void byteCompare() {
        Byte by1 = new Byte("123");
        Byte by2 = new Byte("123");
        int length = Byte.SIZE;

        int max = Byte.MAX_VALUE;
        int min = Byte.MIN_VALUE;

        if(by1 == (by2))
        {
            System.out.println("Operation '=' compares the reference of Byte objects and equal");
        } else {
            System.out.println("Operation '=' compares the objects of Byte objects and not equal");
        }

        if(by1.equals(by2))
        {
            System.out.println("Function 'equals()' compares the value of Byte objects and equal");
        } else {
            System.out.println("Function 'equals()' compares the value of Byte objects and not equal");
        }

        Byte by3 = by1;
        if(by3 == by1)
        {
            System.out.println("Operation '=' compares the reference of Byte objects and equal");
        }else {
            System.out.println("Operation '=' compares the reference of Byte objects and not equal");
        }

        byte byt1 = 123;

        int int1 = 123;

        if(byt1 == int1) {
            System.out.println("byte == int");
        } else {
            System.out.println("byte != int");
        }


    }

    public static void doubleVsLong() {
        Long ts = 1580556965985L;
        Long newts = new Double(ts/1000).longValue();


        System.out.println(newts);
        //1580556965
    }

    public static void intToDouble() {
        Integer max = 200210;
        Integer psize = 200;


        Double r =  new Double((double)max/psize);
        Double re = Math.ceil( r );
        System.out.println(r + " " + re + " " + re.intValue());
    }
}
