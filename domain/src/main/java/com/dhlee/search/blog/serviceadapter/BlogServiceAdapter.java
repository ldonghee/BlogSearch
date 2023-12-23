package com.dhlee.search.blog.serviceadapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.port.LoadBlogCachePort;
import com.dhlee.search.blog.port.LoadBlogExternalPort;
import com.dhlee.search.blog.port.LoadBlogServicePort;
import com.dhlee.search.blog.port.SaveBlogCachePort;

@Service
public class BlogServiceAdapter implements LoadBlogServicePort {
	private final LoadBlogExternalPort loadBlogExternalPort;
	private final LoadBlogCachePort loadBlogCachePort;
	private final SaveBlogCachePort saveBlogCachePort;

	public BlogServiceAdapter(LoadBlogExternalPort loadBlogExternalPort, LoadBlogCachePort loadBlogCachePort, SaveBlogCachePort saveBlogCachePort) {
		this.loadBlogExternalPort = loadBlogExternalPort;
		this.loadBlogCachePort = loadBlogCachePort;
		this.saveBlogCachePort = saveBlogCachePort;
	}

	@Override
	public List<Blog> getBlogs(String keyword, Integer page, Integer size, String sort) {
		List<Blog> blogs = loadBlogCachePort.findBlogs(keyword, page, size, sort);
		if(blogs.isEmpty()) {
			blogs = loadBlogExternalPort.findBlogs(keyword, page, size, sort);
			saveBlogCachePort.saveBlogs(blogs, keyword, page, size, sort);
		}
		return blogs;
	}
}
