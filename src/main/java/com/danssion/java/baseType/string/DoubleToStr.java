/**
 * \* Created with IntelliJ IDEA.
 * \* Name: DoubleToStr
 * \* User: danssion
 * \* Date: 2019/11/22
 * \* Time: 21:49
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.baseType.string;

public class DoubleToStr {
    public static void main(String[] args) {

        Double a = Double.valueOf("0.00001");

        Double b = Double.valueOf("1.98E-6");

        Double c = Double.valueOf("1.1e-07");

        Double d = 0.0;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println(d > 0);

    }

}
