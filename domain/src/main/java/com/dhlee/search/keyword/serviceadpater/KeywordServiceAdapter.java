package com.dhlee.search.keyword.serviceadpater;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhlee.search.keyword.domain.Keyword;
import com.dhlee.search.keyword.port.LoadKeywordPersistencePort;
import com.dhlee.search.keyword.port.LoadKeywordServicePort;

@Service
public class KeywordServiceAdapter implements LoadKeywordServicePort {
	private final LoadKeywordPersistencePort loadKeywordPersistencePort;

	public KeywordServiceAdapter(LoadKeywordPersistencePort loadKeywordPersistencePort) {
		this.loadKeywordPersistencePort = loadKeywordPersistencePort;
	}

	@Override
	public List<Keyword> getPopularKeywords() {
		return loadKeywordPersistencePort.findPopularKeywords();
	}
}
