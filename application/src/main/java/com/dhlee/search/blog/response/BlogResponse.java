package com.dhlee.search.blog.response;

import java.util.List;

import com.dhlee.search.blog.domain.Blog;

public class BlogResponse {
	private final List<Blog> blogs;

	public BlogResponse(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}
}
