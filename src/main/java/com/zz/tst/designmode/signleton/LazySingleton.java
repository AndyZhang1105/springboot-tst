package com.zz.tst.designmode.signleton;

public class LazySingleton {

    private static volatile LazySingleton instance = null;    // volatile 保证 instance 在所有线程中同步
    private LazySingleton() {}    //private 避免类在外部被实例化

    public static synchronized LazySingleton getInstance() {
        //getInstance 方法前加同步
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazySingleton zt1 = LazySingleton.getInstance();
        LazySingleton zt2 = LazySingleton.getInstance();
        if(zt1==zt2) {
            System.out.println("他们是同一个！");
        } else {
            System.out.println("他们不是同一个！");
        }
    }
}
