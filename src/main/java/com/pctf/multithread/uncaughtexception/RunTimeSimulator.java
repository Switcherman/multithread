package com.pctf.multithread.uncaughtexception;

import java.util.Random;

public class RunTimeSimulator {

    public static void main(String[] args) {
        WorkThreadFactory factory = new WorkThreadFactory();
        for (int i = 0; i < 10; i++ ) {
            factory.newThread(new Runnable() {
                @Override
                public void run() {
                    int count = new Random().nextInt(10);
                    if(count % 7 == 0) {
                        int i = 1 / 0;
                    }
                }
            }).start();
        }
    }
}
