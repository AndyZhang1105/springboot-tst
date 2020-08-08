package com.zz.tst.juc;

public class Singleton2Tst {

    private static class SingletonHolder {
        public static Singleton2Tst singleton = new Singleton2Tst();
    }

    public static Singleton2Tst getInstance(){
        return SingletonHolder.singleton;
    }

}
