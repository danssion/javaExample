package com.danssion.java.tryCatch;

public class PrintException {
    public static void main(String[] args) {
        try {
            int res = 1/0;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
