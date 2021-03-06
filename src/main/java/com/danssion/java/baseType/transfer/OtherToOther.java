package com.danssion.java.baseType.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/5/23 16:38
 * @desc
 */
public class OtherToOther {
    public static void main(String[] args) {
        listToString();
        arrayToString();
        byteToBoolean();
    }

    public static void listToString() {
        List<String> lists = Arrays.asList("a", "1", "b", "2", "c");
        String str1 = String.join(",", lists);

        System.out.println("str1:" + str1);
        String str2 = lists.stream().collect(Collectors.joining(":"));

        System.out.println("str2:" + str2);
    }

    public static void arrayToString() {
        String[] arr = new String[]{"a", "1", "b", "2", "c"};

        System.out.println(Arrays.toString(arr));
        List<String> list = Arrays.asList(arr);

        System.out.println(list);
    }

    public static void mapToJsonObj() {
        JSONObject itemJSONObj = new JSONObject();
        Map<String, Object> itemMap = JSONObject.toJavaObject(itemJSONObj, Map.class);
        itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));
    }

    public static void listToJson() {
        // List <= => JSONArray
        String itemJson = "json string";
        List<Object> itemList = JSON.parseArray(itemJson, Object.class);
        JSONArray ja = JSONArray.parseArray(JSON.toJSONString(itemList));

    }

    public static void listToArray() {
        ArrayList<String> list = new ArrayList<String>();
        String[] strings = new String[list.size()];
        list.toArray(strings);
    }

    public static void byteToBoolean() {
        Byte b1 = new Byte("1");
        Byte b2 = new Byte("0");

        System.out.println("Byte:"+b1+" - "+b2);

        System.out.println(b1.byteValue());

        System.out.println("byte:"+b1.byteValue()+" _ "+b2.byteValue());

        Boolean bl1 = new Boolean(b1.toString());
        Boolean bl2 = new Boolean(b2.toString());
        System.out.println("Byte to Boolean:"+bl1+" - "+bl2);
        Boolean bl11 = Boolean.valueOf(b1.toString());
        Boolean bl12 = Boolean.valueOf(b2.toString());
        System.out.println("Byte to Boolean with value:"+bl11+" - "+bl12);

        Integer i1 = Integer.valueOf(b1);
        if(b1 == 0) {
            System.out.println(false);
        } else {
            System.out.println(true);
        }
    }
}
