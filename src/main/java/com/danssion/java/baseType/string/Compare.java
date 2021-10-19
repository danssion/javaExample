package com.danssion.java.baseType.string;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/2 20:51
 * @desc
 */
public class Compare {
    public static void main(String[] args) {
        equalsInString();
        hashVSEquals();
    }

    public static void equalsInString() {
        String s1 = "aaa";
        String s2 = "aa" + new String("a");
        String s3 = new String("aaa");

        System.out.println(s1.intern().equals(s1));
        System.out.println(s1.intern().equals(s2));
        System.out.println(s3.intern().equals(s1));

//        首先 s1.intern.equals(s1) 这个无论如何都返回 true，因为 s1 字符串创建出来就已经在常量池中存在了。

//        然后第二条语句返回 false，因为 s1 返回的是常量池中的对象，而 s2 返回的是堆中的对象
// ????
//        第三条语句 s3.intern.equals(s1)，返回 true ，因为 s3 对象虽然在堆中创建了一个对象，但是 s3 中的 "aaa" 返回的是常量池中的对象。
    }


    public static void hashVSEquals() {
        String str1 = "通话";
        String str2 = "重地";

        //它们两个的 hashcode 相等，但是 equals 可不相等
        System.out.println(str1.equals(str2));
        System.out.println("str1 hash:"+str1.hashCode());
        System.out.println("str2 hash:"+str2.hashCode());
    }
}
