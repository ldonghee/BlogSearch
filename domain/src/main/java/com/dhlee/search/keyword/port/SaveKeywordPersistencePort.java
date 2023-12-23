package com.dhlee.search.keyword.port;

import com.dhlee.search.keyword.domain.Keyword;

public interface SaveKeywordPersistencePort {
	void saveKeyword(Keyword keyword);
}
