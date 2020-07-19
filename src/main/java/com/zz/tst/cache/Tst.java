package com.zz.tst.cache;

import java.util.Iterator;
import java.util.Map;

public class Tst {

    public static void main(String[] args) {
        LRUHashMap lruHashMap = new LRUHashMap(5);

        for (int i = 0; i < 100; i++) {
            lruHashMap.put(String.valueOf(i), i + 1000);
        }

        Iterator<Map.Entry<String, Object>> entries = lruHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
