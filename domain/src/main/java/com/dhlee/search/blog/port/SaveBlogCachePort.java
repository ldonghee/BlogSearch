package com.dhlee.search.blog.port;

import java.util.List;

import com.dhlee.search.blog.domain.Blog;

public interface SaveBlogCachePort {
	void saveBlogs(List<Blog> blogs, String keyword, Integer page, Integer size, String sort);
}
