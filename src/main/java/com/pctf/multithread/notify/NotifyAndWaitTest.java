package com.pctf.multithread.notify;

public class NotifyAndWaitTest {

    private static final Object NOTIFIER_A = new Object();

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Task());
        Thread threadB = new Thread(new Task());
        threadA.start();
        threadB.start();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            while (i <= 100) {
                 synchronized (NOTIFIER_A) {
                     NOTIFIER_A.notify();
                     System.out.println(Thread.currentThread().getName() + " print " + i);
                     i++;
                     if (i < 100) {
                         try {
                             NOTIFIER_A.wait();
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }
            }
        }
    }

}
