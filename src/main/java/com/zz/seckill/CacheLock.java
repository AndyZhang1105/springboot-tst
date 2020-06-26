package com.zz.seckill;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    String lockedPrefix() default ""; // redis 锁key的前缀
    long timeOut() default 10000; // 取锁时间，超过即取锁失败，不再重试，单位是毫秒
    int expireTime() default 30; // key在redis里存在的时间，单位是秒
}