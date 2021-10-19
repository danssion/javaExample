package com.danssion.java.java8;

import org.springframework.asm.Handle;

import java.util.Arrays;

public class Hello8 {

    public static void main(String[] args) {
        Hello8 hello8 = new Hello8();
        hello8.lambda();
    }

    private void lambda() {
        Arrays.asList( "p", "k", "u","f", "o", "r","k").forEach(e -> System.out.println( e ) );
    }
}
