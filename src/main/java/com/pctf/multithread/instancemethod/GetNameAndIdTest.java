package com.pctf.multithread.instancemethod;

public class GetNameAndIdTest {
    public static void main(String[] args) {
        Thread t1 = new MyThread();
        t1.setName("我的线程");
        t1.start();
        System.out.println("----" + t1.getId());
    }
}
