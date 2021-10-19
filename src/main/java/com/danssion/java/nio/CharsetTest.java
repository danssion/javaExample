/**
 * \* Created with IntelliJ IDEA.
 * \* Name: CharsetTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 19:45
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.nio;

import java.nio.charset.*;
import java.util.*;

public class CharsetTest {
    public static void main(String[] args) {
        // 获取Java支持的全部字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()) {
            // 输出字符集的别名和对应的Charset对象
            System.out.println(alias + "----->"
                    + map.get(alias));
        }
    }
}
