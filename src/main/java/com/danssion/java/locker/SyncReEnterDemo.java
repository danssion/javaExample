package com.danssion.java.locker;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/27 18:51
 * @desc  可重入锁，也叫递归锁。它有两层含义，
 * 第一，当一个线程在外层函数得到可重入锁后，能直接递归地调用该函数，
 * 第二，同一线程在外层函数获得可重入锁后，内层函数可以直接获取该锁对应其它代码的控制权。
 * 之前我们提到的 synchronized 和 ReentrantLock 都是可重入锁
 */



public class SyncReEnterDemo {
    public static void main(String[] args) {
        SyncReEnter demo=new SyncReEnter();
        new Thread(demo).start();
        new Thread(demo).start();
    }
}

/**
 * 在第18行第一次启动线程时，在 run 方法里，会调用包含 synchronized 关键字的 get 方法，
 * 这时这个线程会得到 get 方法的锁。当执行到 get 里的 set 方法时，
 * 由于 set 方法也包含 synchronized 关键字，而且 set 是包含在 get 里的，
 * 所以这里无需再次申请 set 的锁，能继续执行。所以通过输出，大家能看到 get 和 set 的打印语句是连续输出的。
 * 同理我们能理解第16行第二次启动线程的输出
 */



class SyncReEnter implements Runnable{
    public synchronized void get(){
        System.out.print(Thread.currentThread().getId() + " get \t");
        //在get方法里调用set
        set();
    }
    public synchronized void set()
    {System.out.print(Thread.currentThread().getId()+" set \t"); }

    @Override
    public void run() //run方法里调用了get方法
    { get();}
}