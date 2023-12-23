package com.dhlee.search.blog.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.search.blog.client.ClientEnginManager;
import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.domain.BlogEntity;
import com.dhlee.search.blog.port.LoadBlogExternalPort;
import com.dhlee.search.blog.reqeust.SearchRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchAdapter implements LoadBlogExternalPort {
	private final ClientEnginManager clientEnginManager;

	@Override
	public List<Blog> findBlogs(String keyword, Integer page, Integer size, String sort) {
		log.info("external find blog");
		BlogEntity blogEntity = clientEnginManager.search(new SearchRequest(keyword, sort, page, size));

		return blogEntity.getBlogEntities()
				.stream()
				.map(entity -> new Blog(entity.getTitle(),
										entity.getUrl(),
										entity.getContents(),
										entity.getBlogname(),
										entity.getDatetime()))
				.collect(Collectors.toList());
	}
}
