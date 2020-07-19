package com.zz.tst.cache;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheObject implements Serializable {

    long createTime;
    long lifeTime;
    Object value;
    AtomicInteger invalidTimes;

    public CacheObject(long lifeTime, Object value) {
        this.lifeTime = lifeTime;
        this.value = value;
        invalidTimes = new AtomicInteger(0);
        this.createTime = System.currentTimeMillis();
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(long lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Object getValue() {
        // 如果已经失效很久，说明很久没有被访问，那么直接返回null，不对失效次数进行判断。
        if ((System.currentTimeMillis() - createTime) > 10 * lifeTime) {
            return null;
        }
        // 保证在高并发下，缓存失效也可以保证较高的命中率
        if (System.currentTimeMillis() - createTime > lifeTime) {
            if (invalidTimes.incrementAndGet() > 3) {
                return value;
            } else {
                return null;
            }
        }
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public AtomicInteger getInvalidTimes() {
        return invalidTimes;
    }

    public void setInvalidTimes(AtomicInteger invalidTimes) {
        this.invalidTimes = invalidTimes;
    }

}
