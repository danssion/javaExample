package com.danssion.java.baseType.string;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeTest {
    public static void main(String[] args) {

        testSpecial();
    }

    public static void testSpecial() {
        String str = "#sdfsdf";
        String encode = "%23sdfsdf";
        String out = null;
        String javaen = null;
        try {
            javaen = URLEncoder.encode(str, "utf8");
            out = URLDecoder.decode(encode, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        System.out.println(str + " ==> " + out + "  java:" + javaen);
    }
}
