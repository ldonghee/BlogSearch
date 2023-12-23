package com.dhlee.search.blog.port;

import java.util.List;

import com.dhlee.search.blog.domain.Blog;

public interface LoadBlogExternalPort {
	List<Blog> findBlogs(String keyword, Integer page, Integer size, String sort);
}
