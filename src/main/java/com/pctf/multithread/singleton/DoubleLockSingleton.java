package com.pctf.multithread.singleton;

/**
 * 双重锁单例模式
 */
public class DoubleLockSingleton {

    private static volatile DoubleLockSingleton INSTANCE;

    private DoubleLockSingleton() {
        System.out.println("=====双重锁单例模式构造");
    }

    public static DoubleLockSingleton getINSTANCE() {
        if(INSTANCE == null) {
            synchronized (DoubleLockSingleton.class) {
                if(INSTANCE == null) {
                    INSTANCE = new DoubleLockSingleton();
                }
            }
        }
        return INSTANCE;
    }

}
