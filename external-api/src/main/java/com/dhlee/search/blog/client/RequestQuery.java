package com.dhlee.search.blog.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestQuery {
	private final String query;
	private final String value;
}
