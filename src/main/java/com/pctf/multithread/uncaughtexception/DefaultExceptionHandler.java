package com.pctf.multithread.uncaughtexception;

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("线程" + t.getName() + "发生异常,异常信息为" + e.getMessage());
        for(StackTraceElement element : e.getStackTrace()) {
            System.out.println(element.getClassName() + element.getMethodName() + element.getLineNumber());
        }
    }
}
