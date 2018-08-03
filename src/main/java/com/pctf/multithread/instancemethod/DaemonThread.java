package com.pctf.multithread.instancemethod;

public class DaemonThread extends Thread {
    private int count = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("守护线程运行中");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
