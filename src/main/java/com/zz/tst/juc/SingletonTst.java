package com.zz.tst.juc;

public class SingletonTst {

    //通过volatile关键字来确保安全
    private volatile static SingletonTst singleton;

    private SingletonTst() {}

    public static SingletonTst getInstance(){
        if(singleton == null){
            synchronized (SingletonTst.class){
                if(singleton == null){
                    singleton = new SingletonTst();
                }
            }
        }
        return singleton;
    }

}
