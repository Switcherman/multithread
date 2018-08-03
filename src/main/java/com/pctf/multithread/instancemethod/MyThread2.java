package com.pctf.multithread.instancemethod;

public class MyThread2 extends Thread {
    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){ }
        System.out.println(Thread.currentThread().getName() + "耗时" + (System.currentTimeMillis() - startTime));
    }
}
