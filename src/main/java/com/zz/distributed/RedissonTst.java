package com.zz.distributed;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.concurrent.TimeUnit;

public class RedissonTst {

    public static RedissonClient getRedissonClient(String address) {
        return getRedissonClient(address, 100, 10, 100, 0, "");
    }

    public static RedissonClient getRedissonClient(String address, int poolSize, int poolMinIdle, int poolTimeout, int database, String password) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer().setAddress(address).setClientName("Single")
                .setConnectionPoolSize(poolSize)
                .setConnectionMinimumIdleSize(poolMinIdle)
                .setConnectTimeout(poolTimeout)
                .setDatabase(database);
        if(StringUtils.isNotBlank(password)) {
            serverConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

    public static void main(String[] args) {
        RedissonClient redissonClient1 = getRedissonClient("127.0.0.1:6379");
        RedissonClient redissonClient2 = getRedissonClient("127.0.0.1:6380");
        RedissonClient redissonClient3 = getRedissonClient("127.0.0.1:6381");

        String resourceName = "REDLOCK_KEY";

        RLock lock1 = redissonClient1.getLock(resourceName);
        RLock lock2 = redissonClient2.getLock(resourceName);
        RLock lock3 = redissonClient3.getLock(resourceName);

        // 向3个redis实例尝试加锁
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        boolean isLock;
        try {
            // isLock = redLock.tryLock();
            // 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。
            isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
            System.out.println("isLock = " + isLock);
            if (isLock) {
                System.out.println("Locked.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 无论如何, 最后都要解锁
            redLock.unlock();
        }
    }

}
