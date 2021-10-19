package com.danssion.java.tryCatch;

public class FinallyReturn {
    public static int testFinally() {
        try {
            return 1;
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("execute finally!");
            return 3;


        }
    }

    public static void main(String[] args) {
        int re = testFinally();
        System.out.println(re);
        System.out.println(12&9);
        int x,a=4,b=6,c=8 ;
        x=++a+b+++c++;
        System.out.println("a:" + a + ",b:"+b+",c:"+c+",x:"+x);



    }
}
