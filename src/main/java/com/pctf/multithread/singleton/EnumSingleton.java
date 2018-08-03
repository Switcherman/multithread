package com.pctf.multithread.singleton;

public enum EnumSingleton {

    INSTANCE;

    private EnumSingleton() {
        System.out.println("=====枚举类单例模式构造");
    }

}
