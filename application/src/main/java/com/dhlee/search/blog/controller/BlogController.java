package com.dhlee.search.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.port.LoadBlogServicePort;
import com.dhlee.search.blog.request.BlogRequest;
import com.dhlee.search.model.CommonResponse;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {

	private final LoadBlogServicePort loadBlogServicePort;

	public BlogController(LoadBlogServicePort loadBlogServicePort) {
		this.loadBlogServicePort = loadBlogServicePort;
	}

	@GetMapping("/search")
	public ResponseEntity<CommonResponse<List<Blog>>> getBlogs(BlogRequest blogRequest) {
		List<Blog> blogs = loadBlogServicePort.getBlogs(blogRequest.getKeyword(),
														blogRequest.getPage(),
														blogRequest.getSize(),
														blogRequest.getSort());

		return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), blogs));
	}
}
