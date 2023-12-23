package com.dhlee.search.blog.client.naver.vo;

import com.dhlee.search.blog.domain.BlogDocumentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String contents;
	@JsonProperty("link")
	private String url;
	@JsonProperty("bloggername")
	private String blogname;
	@JsonProperty("bloggerlink")
	private String thumbnail;
	@JsonProperty("postdate")
	private String datetime;


	public BlogDocumentEntity of() {
		return BlogDocumentEntity.builder()
								 .title(title)
								 .contents(contents)
								 .url(url)
								 .blogname(blogname)
								 .thumbnail(thumbnail)
								 .datetime(datetime)
								 .build();
	}
}
