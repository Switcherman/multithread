package com.pctf.multithread.instancemethod;

public class StartTest {

    public static void main (String[] args) {
        Thread t1 = new MyThread();
        t1.start();

        for(int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000);
                System.out.println("当前运行线程" + Thread.currentThread().getName());
            } catch (Exception e) {

            }
        }
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++ ){
                    Thread.sleep(2000);
                    System.out.println("当前运行线程" + Thread.currentThread().getName());
                }
            } catch (Exception e){

            }
        }
    }
}
