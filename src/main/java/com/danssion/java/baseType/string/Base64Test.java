package com.danssion.java.baseType.string;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) {
//        testSpecialStr();
        testEmoji();
    }

    public static void testSpecialStr() {
        String str1 = "#abdc";
        String encodeStr1=  "I2FiZGM=";

        Base64Test.decodeBase64(str1,encodeStr1);
    }

    public static void testEmoji() {
        String str = "⚽\uD83C\uDFC0null\uD83C\uDFC8🏸";
//        String encode = "4pq977iP8J+PgG51bGzwn4+I8J+PuA==";
        String encode = "I1Jvbmfwn4y577 l77yBIybigKbigKYl4pq977iP8J PgPCfj7g=";

        Base64Test.decodeBase64(str,encode);
    }

    public static void decodeBase64(String str, String toDeStr ) {
        String encodeStr = null;
        String decode = null;
        try {
            encodeStr = Base64.getEncoder().encodeToString(str.getBytes("utf8" ));
            byte[] decodeByte = Base64.getDecoder().decode(toDeStr);
            decode = new String(decodeByte,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(toDeStr+" ("+str+") ==> "+decode+ " / java encode:"+ encodeStr);
    }


}
