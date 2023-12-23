package com.dhlee.search.keyword.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dhlee.search.keyword.domain.Keyword;
import com.dhlee.search.keyword.port.LoadKeywordPersistencePort;
import com.dhlee.search.keyword.port.LoadLockCachePort;
import com.dhlee.search.keyword.port.SaveKeywordPersistencePort;
import com.dhlee.search.keyword.serviceadpater.KeywordServiceAdapter;

@ExtendWith(MockitoExtension.class)
public class KeywordServiceAdapterTest {
	@Mock
	private LoadKeywordPersistencePort loadKeywordPersistencePort;
	@Mock
	private SaveKeywordPersistencePort saveKeywordPersistencePort;
	@Mock
	private LoadLockCachePort loadLockCachePort;

	private KeywordServiceAdapter keywordServiceAdapter;

	@BeforeEach
	public void setUp() {
		keywordServiceAdapter = new KeywordServiceAdapter(loadKeywordPersistencePort, saveKeywordPersistencePort, loadLockCachePort);
	}

	@Test
	public void getPopularKeywords() {
		// given
		List<Keyword> expect = Arrays.asList(new Keyword("TEST", 1L), new Keyword("TEST2", 5L));

		// when
		when(loadKeywordPersistencePort.findPopularKeywords()).thenReturn(expect);
		List<Keyword> actual = keywordServiceAdapter.getPopularKeywords();

		// then
		assertAll(
				() -> assertThat(actual).isNotNull(),
				() -> assertThat(actual.size()).isEqualTo(expect.size()));

	}
}
