package com.pctf.multithread.singleton;

/**
 * 静态内部类单例模式
 */
public class InnerClassSingleton {

    private InnerClassSingleton() {
        System.out.println("====静态内部类单例模式构造");
    }

    private static class InnerClass {
        static InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    public InnerClassSingleton getInstance() {
        return InnerClass.INSTANCE;
    }

}
