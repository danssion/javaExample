/**
 * \* Created with IntelliJ IDEA.
 * \* Name: MyUtils
 * \* User: danssion
 * \* Date: 2019/5/10
 * \* Time: 17:18
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUtils {
    // 下面dest集合元素类型必须与src集合元素类型相同，或是其父类
    public static <T> T copy(Collection<? super T> dest
            , Collection<T> src) {
        T last = null;
        for (T ele : src) {
            last = ele;
            dest.add(ele);
        }
        return last;
    }

    public static void main(String[] args) {
        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        li.add(5);
        // 此处可准确的知道最后一个被复制的元素是Integer类型
        // 与src集合元素的类型相同
        Integer last = copy(ln, li);    // ①
        System.out.println(ln);
    }
}