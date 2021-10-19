/**
 * \* Created with IntelliJ IDEA.
 * \* Name: Invalid
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 18:17
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

import java.util.ArrayList;
import java.util.List;

public class Invalid {
    public static void main(String[] args) {
//        List<String>[] lsa = new List<String>[10];

        List<String>[] lsa = new ArrayList[10];
        Object[] oa = (Object[]) lsa;
        List<Integer> li = new ArrayList<>();
        li.add(new Integer(3));

        oa[1] = li;
        //下面代码引发异常
        String s = lsa[1].get(0);


    }
}
