package com.dhlee.search.blog.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchResult {
	private final String title;
	private final String url;
	private final String contents;
	private final String blogName;
	private final String datetime;
}
