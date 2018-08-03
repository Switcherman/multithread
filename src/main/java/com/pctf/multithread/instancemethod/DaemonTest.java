package com.pctf.multithread.instancemethod;

public class DaemonTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new DaemonThread();
        t1.setName("守护线程");
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(10000);
        System.out.println("主线程结束");
    }
}
