package com.dhlee.search.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dhlee.search.blog.client.kakao.KakaoClientEngine;
import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.domain.BlogEntity;
import com.dhlee.search.blog.reqeust.SearchRequest;

public class KaKaoClientEngineTest {
	private KakaoClientEngine engine = new KakaoClientEngine();

	@Test
	@DisplayName("검색 테스트")
	public void search() {
		// given
		SearchRequest request = new SearchRequest("TEST", "accuracy", 1, 10);

		// when
		BlogEntity blogEntity = engine.search(request).block();
		assert blogEntity != null;
		List<Blog> blogs = blogEntity.getBlogEntities()
									 .stream()
									 .map(entity -> new Blog(entity.getTitle(),
															 entity.getUrl(),
															 entity.getContents(),
															 entity.getBlogname(),
															 entity.getDatetime()))
									 .collect(Collectors.toList());

		// then
		assertAll(
				() -> assertThat(blogs).isNotNull(),
				() -> assertThat(blogs.size()).isEqualTo(10));

	}
}
