package com.pctf.multithread.staticmethod;


public class MyThread3 extends Thread {

    static {
        System.out.println("执行静态块的线程为" + Thread.currentThread().getName());
    }

    public MyThread3() {
        System.out.println("执行构造方法的线程为" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("执行run()方法的当前线程为" + Thread.currentThread().getName());
    }


}
