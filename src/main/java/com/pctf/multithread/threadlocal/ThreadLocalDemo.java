package com.pctf.multithread.threadlocal;

import java.util.Random;

public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> localCount = new ThreadLocal<Integer>(){
        @Override
        public Integer initialValue() {
            return new Integer(0);
        }
    };

    public static void main(String[] args) {
        for(int i = 0; i < 5; i ++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int times = new Random().nextInt(1000);
                    for (int i = 0; i < times; i++) {
                        Integer count = localCount.get();
                        count += 1;
                        localCount.set(count);
                    }
                    System.out.println(Thread.currentThread().getName() + "的计数器为" + localCount.get());
                }
            });
            t.setName("工作线程" + i);
            t.start();
        }
    }
}
