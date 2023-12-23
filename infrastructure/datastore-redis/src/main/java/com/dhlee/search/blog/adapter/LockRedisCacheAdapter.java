package com.dhlee.search.blog.adapter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import com.dhlee.search.keyword.port.LoadLockCachePort;

@Service
public class LockRedisCacheAdapter implements LoadLockCachePort {
	private static final int WAIT_TIME = 10;
	private static final int LEASE_TIME = 1;
	private final RedissonClient redissonClient;

	public LockRedisCacheAdapter(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	@Override
	public Boolean isLock(String lockKey) {
		try {
			return getLock(lockKey).tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			return false;
		}
	}

	@Override
	public void unLock(String lockKey) {
		RLock lock = getLock(lockKey);
		if (!Objects.isNull(lock) && lock.isLocked()) {
			lock.unlock();
		}
	}

	private RLock getLock(String lockKey) {
		return redissonClient.getLock(lockKey);
	}
}
