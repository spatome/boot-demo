package com.spatome.boot.common.config.redis;

import org.redisson.api.RLock;

/**
 * Lock接口
 *
 */
public interface DistributedLock {

    RLock lock(String lockKey);
    RLock lock(String lockKey, long timeout);

    boolean tryLock(String lockKey, long waitTime, long leaseTime);
    boolean tryLock(String lockKey, long waitTime);

    void unlock(String lockKey);
    void unlock(RLock lock);

}
