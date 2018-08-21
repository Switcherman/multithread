package com.pctf.multithread.uncaughtexception;

import java.util.concurrent.ThreadFactory;

public class WorkThreadFactory implements ThreadFactory {

    private static final DefaultExceptionHandler exceptionHandler = new DefaultExceptionHandler();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new ThreadTemplate(r);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        return thread;
    }

    class ThreadTemplate extends Thread{

        public ThreadTemplate(Runnable r) {
            super(r);
        }

        @Override
        public String toString() {
            return this.getName() + this.getId();
        }
    }
}
