package com.dhlee.search.blog.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogDocumentEntity {
	private String title;
	private String contents;
	private String url;
	private String blogname;
	private String thumbnail;
	private String datetime;
}
