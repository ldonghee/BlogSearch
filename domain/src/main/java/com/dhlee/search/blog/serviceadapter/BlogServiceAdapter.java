package com.dhlee.search.blog.serviceadapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.port.LoadBlogExternalPort;
import com.dhlee.search.blog.port.LoadBlogServicePort;

@Service
public class BlogServiceAdapter implements LoadBlogServicePort {
	private final LoadBlogExternalPort loadBlogExternalPort;

	public BlogServiceAdapter(LoadBlogExternalPort loadBlogExternalPort) {
		this.loadBlogExternalPort = loadBlogExternalPort;
	}

	@Override
	public List<Blog> getBlogs(String keyword, Integer page, Integer size, String sort) {
		return loadBlogExternalPort.findBlogs(keyword, page, size, sort);
	}
}
