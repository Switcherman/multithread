package com.pctf.multithread.instancemethod;

public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        System.out.println("当前运行线程" + Thread.currentThread().getName());
    }
}
