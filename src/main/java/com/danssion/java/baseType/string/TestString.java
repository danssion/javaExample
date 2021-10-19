package com.danssion.java.baseType.string;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestString {
    public static void testString() {
        String s="Hello";
        System.out.println(s.substring(0,1));


        String s1="010111";

        System.out.println(Integer.valueOf(s1.substring(0,1)) == 1);
        System.out.println(s1);
        System.out.println(Integer.parseInt(s1.substring(1,1+1)) == 1);
        System.out.println(Integer.parseUnsignedInt(s1.substring(2,2+1)) == 1);

        String url = "/bettor/cstrategy/grid/calc_pre_info?timestamp=1600940691&code=d328367e8c91bc9108407";
        System.out.println(url.substring(0,url.indexOf("?")));
//        System.out.println(url.substring(0,url.indexOf("#")));
        System.out.println(StringUtils.substring(url,0,url.indexOf("#")));
    }



    public static void main(String[] args) {
        testString();
        strToArray();
    }

    public static void strToArray() {
        String s = "0,1,2,3";
        System.out.println(s);
        List<String> list = Arrays.asList(s.split(","));
        System.out.println(list);
    }

}
