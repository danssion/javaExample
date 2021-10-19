/**
 * \* Created with IntelliJ IDEA.
 * \* Name: StreamSplit
 * \* User: danssion
 * \* Date: 2019/9/23
 * \* Time: 22:54
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamSplit {

    public static void main(String[] args) {
//        split();
//        flatMap();
        testArray();
    }

    public static void flatMap() {
        Set<String> set = new HashSet<>();
        set.add("123");set.add("1234");set.add("3425");set.add("842");set.add("38723");
        set.add("3333");set.add("34234");

        set.stream().flatMap(v -> {
            System.out.println(v);
            return null;
        });

        List<String> list = new ArrayList<>(set);

        System.out.println(list.toString());
    }

    public static void split() {
        List<String> list = new ArrayList<>();
        list.add("123");list.add("323");list.add("124");list.add("523");
        list.add("2123");list.add("1231");list.add("-263031713123123123402706841481");


        Long ii = Long.MAX_VALUE;
        System.out.println(ii.toString().length());

        Map<Integer, List<String>> collectPart = list.stream().
                filter(i -> i.length() < new Long(Long.MAX_VALUE).toString().length()
                        && Long.valueOf(i) > 0
                ).collect(
                Collectors.groupingBy(v -> Integer.valueOf(v) % 3
                ));

        System.out.println(collectPart);

        Set<String> set = new HashSet<>();
        set.add("123");set.add("1234");set.add("3425");set.add("842");set.add("38723");
        set.add("3333");set.add("34234");

        Map<Integer, List<String>> collectPart1 = set.stream().collect(
                Collectors.groupingBy(v -> Integer.valueOf(v) % 3
                ));

        System.out.println(collectPart1);
        System.out.println(collectPart1.get(1));
    }


    public static void testArray() {
        Set<String> set = new HashSet<>();
        set.add("123");set.add("1234");set.add("3425");set.add("842");set.add("38723");
        set.add("3333");set.add("34234");


        List<String> list = new ArrayList<>(set);
        list.add("123");list.add("323");list.add("124");list.add("523");
        list.add("2123");list.add("1231");list.add("11");


        Map<Integer, List<String>> contractCollectPart = list.stream().
                filter(i -> i.length() < new Long(Long.MAX_VALUE).toString().length()
                        && Long.valueOf(i) > 0).collect(
                Collectors.groupingBy( v -> Integer.valueOf(v) % 98
                ));

        System.out.println(contractCollectPart);
        for (int i = 0; i < 98 ; i ++) {
            if(contractCollectPart.containsKey(i)) {
                System.out.println("get by "+i+" : "+contractCollectPart.get(i));
            } else {
                System.out.println("null");
            }
        }

    }
}
