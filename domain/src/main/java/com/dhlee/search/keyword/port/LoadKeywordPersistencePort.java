package com.dhlee.search.keyword.port;

import java.util.List;

import com.dhlee.search.keyword.domain.Keyword;

public interface LoadKeywordPersistencePort {
	List<Keyword> findPopularKeywords();
}
