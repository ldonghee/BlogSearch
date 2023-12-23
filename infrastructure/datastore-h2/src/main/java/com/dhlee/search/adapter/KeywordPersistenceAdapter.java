package com.dhlee.search.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhlee.search.entity.KeywordEntity;
import com.dhlee.search.keyword.domain.Keyword;
import com.dhlee.search.keyword.port.LoadKeywordPersistencePort;
import com.dhlee.search.repository.KeywordRepository;

@Service
public class KeywordPersistenceAdapter implements LoadKeywordPersistencePort {
	private final KeywordRepository keywordRepository;

	public KeywordPersistenceAdapter(KeywordRepository keywordRepository) {
		this.keywordRepository = keywordRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Keyword> findPopularKeywords() {
		List<KeywordEntity> keywordEntities = keywordRepository.findTop10ByOrderByCountDesc();

		return keywordEntities.stream()
				.map(entity -> new Keyword(entity.getQuery(), entity.getCount()))
				.collect(Collectors.toList());
	}
}
