package com.dhlee.search.blog.client;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class RequestHeader {
	private final String headerKey;
	private final String headerValue;
}
