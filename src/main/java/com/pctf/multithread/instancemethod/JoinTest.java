package com.pctf.multithread.instancemethod;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread();
        t1.setName("子线程1");
        t1.start();
        t1.join(1000);
        System.out.println("等到子线程1执行完后执行后面的语句");
    }
}
