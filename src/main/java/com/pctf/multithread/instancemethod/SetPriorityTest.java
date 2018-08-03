package com.pctf.multithread.instancemethod;

public class SetPriorityTest {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            Thread t1 = new MyThread2();
            Thread t2 = new MyThread2();
            t1.setPriority(10);
            t2.setPriority(1);
            t1.setName("线程1");
            t2.setName("线程2");
            t1.start();
            t2.start();
        }
    }
}
