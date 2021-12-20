package com.danssion.java.reflect;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/9 17:44
 * @desc
 */
class ReflectPointer {

    public int x = 0;
    private int y = 0;
    public String str1 = "ball";
    public String str2 = "basketball";
    public String str3 = "itcat";

    public ReflectPointer(int x, int y) {//alt + shift +s相当于右键source
        super();
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "ReflectPointer [str1=" + str1 + ", str2=" + str2 + ", str3="
                + str3 + "]";
    }
}
