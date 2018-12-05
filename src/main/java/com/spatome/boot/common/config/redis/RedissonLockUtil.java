package com.spatome.boot.common.config.redis;

import org.redisson.api.RLock;

public class RedissonLockUtil {

    private static DistributedLock redisLock;
    
    public static void setLocker(DistributedLock lock) {
    	redisLock = lock;
    }

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        return redisLock.lock(lockKey);
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public static void unlock(String lockKey) {
    	redisLock.unlock(lockKey);
    }
    
    /**
     * 释放锁
     * @param lock
     */
    public static void unlock(RLock lock) {
    	redisLock.unlock(lock);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：毫秒
     */
    public static RLock lock(String lockKey, long timeout) {
        return redisLock.lock(lockKey, timeout);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        return redisLock.tryLock(lockKey, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @return
     */
    public static boolean tryLock(String lockKey, long waitTime) {
        return redisLock.tryLock(lockKey, waitTime);
    }
}