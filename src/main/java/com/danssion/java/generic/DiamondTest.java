/**
 * \* Created with IntelliJ IDEA.
 * \* Name: DiamondTest
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 15:45
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondTest {
    public static void main(String[] args) {
        List<String> books = new ArrayList<>();
        books.add("Java book");
        books.add("crzy java book");

        books.forEach(el -> System.out.println(el.length()));

        // Java自动推断出HashMap的<>里应该是String , List<String>
        Map<String, List<String>> schoolsInfo = new HashMap<>();
        // Java自动推断出ArrayList的<>里应该是String
        List<String> schools = new ArrayList<>();
        schools.add("斜月三星洞");
        schools.add("西天取经路");
        schoolsInfo.put("孙悟空", schools);
        // 遍历Map时，Map的key是String类型，value是List<String>类型
        schoolsInfo.forEach((key, value) -> System.out.println(key + "-->" + value));
    }
}
