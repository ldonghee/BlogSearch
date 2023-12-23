package com.dhlee.search.keyword.serviceadpater;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhlee.search.keyword.domain.Keyword;
import com.dhlee.search.keyword.port.LoadKeywordPersistencePort;
import com.dhlee.search.keyword.port.LoadKeywordServicePort;
import com.dhlee.search.keyword.port.LoadLockCachePort;
import com.dhlee.search.keyword.port.SaveKeywordPersistencePort;
import com.dhlee.search.keyword.port.SaveKeywordServicePort;

@Service
public class KeywordServiceAdapter implements LoadKeywordServicePort, SaveKeywordServicePort {
	private final LoadKeywordPersistencePort loadKeywordPersistencePort;
	private final SaveKeywordPersistencePort saveKeywordPersistencePort;
	private final LoadLockCachePort loadLockCachePort;

	private static final String LOCK_KEY = "lock_keyword";
	public KeywordServiceAdapter(LoadKeywordPersistencePort loadKeywordPersistencePort,
								 SaveKeywordPersistencePort saveKeywordPersistencePort,
								 LoadLockCachePort loadLockCachePort) {
		this.loadKeywordPersistencePort = loadKeywordPersistencePort;
		this.saveKeywordPersistencePort = saveKeywordPersistencePort;
		this.loadLockCachePort = loadLockCachePort;
	}

	@Override
	public List<Keyword> getPopularKeywords() {
		return loadKeywordPersistencePort.findPopularKeywords();
	}

	@Override
	public void saveKeyword(String keyword) {
		Boolean lock = loadLockCachePort.isLock(LOCK_KEY);
		if (lock) {
			saveKeywordPersistencePort.saveKeyword(new Keyword(keyword));
		}
		loadLockCachePort.unLock(LOCK_KEY);
	}
}
