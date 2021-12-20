package com.danssion.java.baseType;

import java.util.*;

public class list {
    public static void main(String[] args) {

        listTrans();
    }

    private void listfor() {
        List<String> list = new ArrayList<String>();

        /**
         * 方法一：最普通的不加思考的写法
         * <p>
         * 优点：较常见，易于理解
         * <p>
         * 缺点：每次都要计算list.size()
         */
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        /**
         * 方法二：数组长度提取出来
         * <p>
         * 优点：不必每次都计算
         * <p>
         * 缺点：1、m的作用域不够小，违反了最小作用域原则 2、不能在for循环中操作list的大小，比如除去或新加一个元素
         */
        int m = list.size();
        for (int i = 0; i < m; i++) {
            System.out.println(list.get(i));
        }
        /**
         * 方法三：数组长度提取出来
         * <p>
         * 优点：1、不必每次都计算 2、所有变量的作用域都遵循了最小范围原则
         * <p>
         * 缺点：1、m的作用域不够小，违反了最小作用域原则 2、不能在for循环中操作list的大小，比如除去或新加一个元素
         */
        for (int i = 0, n = list.size(); i < n; i++) {
            System.out.println(list.get(i));
        }
        /**
         * 方法四：采用倒序的写法
         * <p>
         * 优点：1、不必每次都计算 2、所有变量的作用域都遵循了最小范围原则
         * <p>
         * 缺点：1、结果的顺序会反 2、看起来不习惯，不易读懂
         * <p>
         * 适用场合：与显示结果顺序无关的地方：比如保存之前数据的校验
         */
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
        /**
         * 方法五：Iterator遍历
         * <p>
         * 优点：简洁
         * <p>
         * 缺点：
         */
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        /**
         * 方法六：jdk1.5新写法
         * <p>
         * 优点：简洁结合泛型使用更简洁
         * <p>
         * 缺点：jdk1.4向下不兼容
         */
        for (Object o : list) {
            System.out.println(o);
        }

        /* 建立一个数组 */
        int[] integers = {1, 2, 3, 4};
        /* 开始遍历 */
        for (int i : integers) {
            System.out.println(i); /* 依次输出“1”、“2”、“3”、“4” */
        }
    }

    public static void listTrans() {
        List<String> strList = new ArrayList<>();
        strList.add("id");
        strList.add("<20200312063928.1.E57A21281C0F6EB5@mg.mytokenmsg.com>");
        strList.add("message");
        strList.add("Queued. Thank you.");
        strList.add("message");

        HashMap<String, String> hs = new HashMap<>();
        hs.put("id", "<20200312063928.1.E57A21281C0F6EB5@mg.mytokenmsg.com>");


        String[] strArr = strList.toArray(new String[0]);

        for (String str : strArr) {
            System.out.println(str);
        }


    }
}
