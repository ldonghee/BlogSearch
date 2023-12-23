package com.dhlee.search.blog.client.kakao.vo;

import lombok.Getter;

import com.dhlee.search.blog.domain.BlogDocumentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class Document {
	@JsonProperty("title")
	private String title;
	@JsonProperty("contents")
	private String contents;
	@JsonProperty("url")
	private String url;
	@JsonProperty("blogname")
	private String blogname;
	@JsonProperty("thumbnail")
	private String thumbnail;
	@JsonProperty("datetime")
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
