package com.danssion.java.baseType.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/4/6 17:00
 * @desc JavaExample-DdsignPattern
 */
public class OperatedSafeExp {
    public static void main(String[] args) {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");

        //迭代器中循环删除数据安全。
//        iteratorExp(map);

        /**
         * del:1
         * Exception in thread "main" java.util.ConcurrentModificationException
         * 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1429)
         * 	at java.util.HashMap$EntryIterator.next(HashMap.java:1463)
         * 	at java.util.HashMap$EntryIterator.next(HashMap.java:1461)
         * 	at com.danssion.java.baseType.Map.OperatedSafeExp.forExp(OperatedSafeExp.java:45)
         * 	at com.danssion.java.baseType.Map.OperatedSafeExp.main(OperatedSafeExp.java:26)
         *
         * 	For 循环中删除数据非安全
         */
//        forExp(map);

        /**
         * del:1
         * show:2
         * show:3
         * show:4
         * show:5
         * Exception in thread "main" java.util.ConcurrentModificationException
         * 	at java.util.HashMap.forEach(HashMap.java:1283)
         * 	at com.danssion.java.baseType.Map.OperatedSafeExp.lambdaExp(OperatedSafeExp.java:69)
         * 	at com.danssion.java.baseType.Map.OperatedSafeExp.main(OperatedSafeExp.java:39)
         * 	Lambda 循环中删除数据非安全
         */
//        lambdaExp(map);
//        lambdaCurrectExp(map);

        /**
         * del:1
         * show:2
         * show:3
         * show:4
         * show:5
         * Exception in thread "main" java.util.ConcurrentModificationException
         * 	at java.util.HashMap$EntrySpliterator.forEachRemaining(HashMap.java:1688)
         * 	at java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:580)
         * 	at com.danssion.java.baseType.Map.OperatedSafeExp.streamExp(OperatedSafeExp.java:103)
         * 	at com.danssion.java.baseType.Map.OperatedSafeExp.main(OperatedSafeExp.java:54)
         * 	Stream 循环中删除数据非安全
         */
//        streamExp(map);
        //Stream 中的 filter 过滤掉无用的数据，再进行遍历也是一种安全的操作集合的方式
        streamCurrectExp(map);
    }

    public static void iteratorExp(Map map) {
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() == 1) {
                // 删除
                System.out.println("del:" + entry.getKey());
                iterator.remove();
            } else {
                System.out.println("show:" + entry.getKey());
            }
        }
    }

    public static void forExp(Map<Integer,String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                // 删除
                System.out.println("del:" + entry.getKey());
                map.remove(entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        }
    }

    public static void lambdaExp(Map<Integer,String> map) {
        map.forEach((key, value) -> {
            if (key == 1) {
                System.out.println("del:" + key);
                map.remove(key);
            } else {
                System.out.println("show:" + key);
            }
        });
    }

    public static void lambdaCurrectExp(Map<Integer,String>map) {
        // 根据 map 中的 key 去判断删除
        map.keySet().removeIf(key -> key == 1);
        map.forEach((key, value) -> {
            System.out.println("show:" + key);
        });
    }

    public static void streamExp(Map<Integer,String> map) {
        map.entrySet().stream().forEach((entry) -> {
            if (entry.getKey() == 1) {
                System.out.println("del:" + entry.getKey());
                map.remove(entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        });
    }

    public static void streamCurrectExp(Map<Integer,String> map) {
        map.entrySet().stream().filter(m -> 1 != m.getKey()).forEach((entry) -> {
            if (entry.getKey() == 1) {
                System.out.println("del:" + entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        });
    }
}
