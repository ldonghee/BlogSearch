package com.dhlee.search.keyword.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KeywordTest {
	@Test
	@DisplayName("생성 테스트")
	public void create() {
		// given
		Keyword keyword = new Keyword("TEST", 2L);

		// when & then
		assertThat(keyword.getCount()).isEqualTo(2L);
	}
}
