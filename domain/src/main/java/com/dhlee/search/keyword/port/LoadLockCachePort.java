package com.dhlee.search.keyword.port;

public interface LoadLockCachePort {
	Boolean isLock(String lockKey);
	void unLock(String lockKey);
}
