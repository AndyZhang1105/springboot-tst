package com.zz.tst.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUHashMap extends LinkedHashMap<String, Object> {

    private int MAX_ENTRIES;

    public LRUHashMap(int size) {
        MAX_ENTRIES = size;
    }

    protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
        return size() > MAX_ENTRIES;
    }

}