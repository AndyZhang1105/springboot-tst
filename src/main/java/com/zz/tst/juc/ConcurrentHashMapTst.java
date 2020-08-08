package com.zz.tst.juc;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTst {

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(8);

        for (int i = 0; i < 100; i++) {
            concurrentHashMap.put(i, i);
        }
        System.out.println(concurrentHashMap);
    }
}
