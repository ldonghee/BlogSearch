package com.dhlee.search.blog.reqeust;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchRequest {
	private final String query;
	private final String sort;
	private final int page;
	private final int size;
}
