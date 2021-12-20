package com.danssion.java.construct;

public class Ex {
    public static void main(String[] args) {
        Fx f = new Fx(5);
    }

    Ex() {
        System.out.println("Ex, no args");
    }

    Ex(int i) {
        System.out.println("Ex int");
    }
}

class Fx extends Ex {
    Fx() {
        super();
        System.out.println("Fx,no-args");
    }

    Fx(int i) {
        this();
        System.out.println("i = [" + i + "],Fx int");
    }
}