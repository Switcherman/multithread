package com.pctf.multithread.instancemethod;

public class IsAliveTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread();
        System.out.println("线程1是否存活" + t1.isAlive());
        t1.start();
        System.out.println("线程1是否存活" + t1.isAlive());
        Thread.sleep(3000);
        System.out.println("线程1是否存活" + t1.isAlive());

    }
}
