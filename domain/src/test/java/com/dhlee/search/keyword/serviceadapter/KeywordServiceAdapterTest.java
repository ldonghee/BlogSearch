package com.dhlee.search.keyword.serviceadapter;

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
import com.dhlee.search.keyword.serviceadpater.KeywordServiceAdapter;

@ExtendWith(MockitoExtension.class)
public class KeywordServiceAdapterTest {
	@Mock
	private LoadKeywordPersistencePort loadKeywordPersistencePort;

	private KeywordServiceAdapter keywordServiceAdapter;

	@BeforeEach
	public void setUp() {
		keywordServiceAdapter = new KeywordServiceAdapter(loadKeywordPersistencePort);
	}

	@Test
	public void getPopularKeywords() {
		List<Keyword> expect = Arrays.asList(new Keyword("TEST", 1L), new Keyword("TEST2", 5L));
		// given
		when(loadKeywordPersistencePort.findPopularKeywords()).thenReturn(expect);

		// when
		List<Keyword> actual = keywordServiceAdapter.getPopularKeywords();

		// then
		assertAll(
				() -> assertThat(actual).isNotNull(),
				() -> assertThat(actual.size()).isEqualTo(expect.size()));

	}
}
