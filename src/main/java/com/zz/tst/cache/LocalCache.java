package com.zz.tst.cache;

public class LocalCache {

    LRUHashMap cacheArea = new LRUHashMap(20);

    public Object get(String key) {
        CacheObject cacheObject = (CacheObject) cacheArea.get(key);
        return cacheObject == null ? null : cacheObject.getValue();
    }

    public void put(String key, Object value, long lifeTime) {
        CacheObject cacheObject = new CacheObject(lifeTime, value);
        cacheArea.put(key, cacheObject);
    }
}
