package com.dhlee.search.blog.serviceadpater;

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

import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.port.LoadBlogExternalPort;
import com.dhlee.search.blog.serviceadapter.BlogServiceAdapter;

@ExtendWith(MockitoExtension.class)
public class BlogServiceAdapterTest {
	@Mock
	private LoadBlogExternalPort loadBlogExternalPort;
	private BlogServiceAdapter blogServiceAdapter;

	@BeforeEach
	public void setUp() {
		blogServiceAdapter = new BlogServiceAdapter(loadBlogExternalPort);
	}

	@Test
	public void getPopularKeywords() {
		// given
		List<Blog> expect = Arrays.asList(new Blog("Test", "Url", "Content", "name", "20231223"),
										  new Blog("Test2", "Url2", "Content2", "name2", "20231223"),
										  new Blog("Test3", "Url3", "Content3", "name3", "20231223"));
		// when
		when(loadBlogExternalPort.findBlogs("TEST", 1, 3, "accuracy")).thenReturn(expect);
		List<Blog> actual = blogServiceAdapter.getBlogs("TEST", 1, 3, "accuracy");

		// then
		assertAll(
				() -> assertThat(actual).isNotNull(),
				() -> assertThat(actual.size()).isEqualTo(expect.size()));
	}
}
