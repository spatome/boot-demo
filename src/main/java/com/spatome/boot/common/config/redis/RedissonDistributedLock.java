package com.spatome.boot.common.config.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * Lock实现类
 *
 */
public class RedissonDistributedLock implements DistributedLock {

	private RedissonClient redissonClient;

	public RedissonDistributedLock(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	@Override
	public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();

        return lock;
	}

	@Override
	public RLock lock(String lockKey, long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, TimeUnit.MILLISECONDS);

        return lock;
	}

	@Override
	public boolean tryLock(String lockKey, long waitTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, -1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
	}

	@Override
	public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
	}

	@Override
	public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
	}

	@Override
	public void unlock(RLock lock) {
		lock.unlock();
	}

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

}
