package com.dhlee.search.blog.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlogTest {
	@Test
	@DisplayName("생성 테스트")
	public void create() {
		// given
		Blog blog = new Blog("Title", "http://localhost:8080.com", "Content", "blogName", "20231223");

		// when & then
		assertAll(
				() -> assertThat(blog.getTitle()).isEqualTo("Title"),
				() -> assertThat(blog.getUrl()).isEqualTo("http://localhost:8080.com"),
				() -> assertThat(blog.getContents()).isEqualTo("Content"),
				() -> assertThat(blog.getBlogName()).isEqualTo("blogName"),
				() -> assertThat(blog.getDatetime()).isEqualTo("20231223"));
	}
}
