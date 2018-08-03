package com.pctf.multithread.staticmethod;

/**
 * yield放弃CPU执行时间.放弃时间不一定.
 *
 */
public class YieldTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for(int i = 0; i < 500000; i ++) {
                    Thread.yield();
                }
                System.out.println("执行时间为" + (System.currentTimeMillis() - startTime));
            }
        }).start();
    }
}
