package com.zz.tst.juc;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapTst {

    public static void main(String[] args) {
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        for (int i = 0; i < 100; i++) {
            concurrentSkipListMap.put(i, i + 1000);
        }
        System.out.println(concurrentSkipListMap);
    }
}
