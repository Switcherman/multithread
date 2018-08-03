package com.pctf.multithread.staticmethod;

import java.util.Date;

/**
 * 放弃CPU执行权,不放弃锁
 */
public class MyThread4 extends Thread {

    @Override
    public void run() {
        System.out.println("开始时间" + new Date());
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        System.out.println("结束时间" + new Date());
    }

}
