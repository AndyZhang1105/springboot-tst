package com.zz.tst.designmode.signleton;

public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton(); // 保证在调用 getInstance 方法之前单例已经存在了
    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return instance;
    }
}
