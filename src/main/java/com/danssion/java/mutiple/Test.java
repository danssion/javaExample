package com.danssion.java.mutiple;

class Base {
    public Base() {
        g();
    }

    public void f() {
        System.out.println("gase f()");
    }

    public void g() {
        System.out.println("Base g()");
    }
}

class Derived extends Base {
    public void f() {
        System.out.println("Derived f()");
    }

    public void g() {
        System.out.println("Derived g()");
    }
}


public class Test {
    public static void main(String[] args) {
        Base b = new Derived();
        b.f();
        b.g();
    }
}
