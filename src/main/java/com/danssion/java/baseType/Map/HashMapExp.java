package com.danssion.java.baseType.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/4/2 17:11
 * @desc JavaExample-DdsignPattern
 */
public class HashMapExp {
    public static void main(String[] args) {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");

        entrySetExp(map);
    }

    public static void entrySetExp(Map map) {
        // 遍历
       Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
       while (iterator.hasNext()) {
           Map.Entry<Integer,String> entry = iterator.next();
           System.out.println(entry.getKey());
           System.out.println(entry.getValue());
       }
    }

    public static void keySetExp(Map map) {
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer k = iterator.next();
            System.out.println(k);
            System.out.println(map.get(k));
        }
    }

    public static void forEntrySetExp(HashMap<Integer,String> map) {
        // 创建并赋值 HashMap

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    public static void forKesSetExp(Map<Integer,String> map) {
        for(Integer key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    public static void lambdaExp(Map map) {
        map.forEach((key,value) ->{
            System.out.println(key);
            System.out.println(value);
        });
    }

    /**
     * Streams API 单线程
     * @param map
     */
    public static void streamApiExp(HashMap<Integer,String> map) {
        // 创建并赋值 HashMap
//        Map<Integer, String> map = new HashMap();
//        map.put(1, "Java");
//        map.put(2, "JDK");
//        map.put(3, "Spring Framework");
//        map.put(4, "MyBatis framework");
//        map.put(5, "Java中文社群");
        // 遍历
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }

    /**
     * Streams API 多线程
     * @param map
     */
    public static void streamApiPExp(HashMap<Integer,String> map) {
        // 遍历
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }


}
