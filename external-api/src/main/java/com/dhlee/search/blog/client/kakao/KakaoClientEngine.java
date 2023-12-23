package com.dhlee.search.blog.client.kakao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dhlee.search.blog.client.ClientEngine;
import com.dhlee.search.blog.client.ClientFrame;
import com.dhlee.search.blog.client.ClientResponseBody;
import com.dhlee.search.blog.client.RequestHeader;
import com.dhlee.search.blog.client.RequestQuery;
import com.dhlee.search.blog.reqeust.SearchRequest;

@Component
public class KakaoClientEngine implements ClientEngine {
	private static final String PREFIX_AUTH_KEY = "KakaoAK ";
	private static final String HEADER_AUTH_KEY = "Authorization";
	private static final String QUERY_KEY = "query";
	private static final String SORT_KEY = "sort";
	private static final String PAGE_KEY = "page";
	private static final String SIZE_KEY = "size";

	private final String apiKey = "547230178f4b06d8b44372be61f736a4";
	private final String host = "https://dapi.kakao.com";
	private final String path = "/v2/search/blog";


	@Override
	public ClientFrame of(SearchRequest searchRequest) {
		return ClientFrame.builder()
				.host(host)
				.path(path)
				.headers(List.of(new RequestHeader(HEADER_AUTH_KEY, PREFIX_AUTH_KEY + apiKey)))
				.params(Arrays.asList(new RequestQuery(QUERY_KEY, searchRequest.getQuery()),
									  new RequestQuery(SORT_KEY, searchRequest.getSort()),
									  new RequestQuery(PAGE_KEY, String.valueOf(searchRequest.getPage())),
									  new RequestQuery(SIZE_KEY, String.valueOf(searchRequest.getSize()))))
				.build();
	}

	@Override
	public Class<? extends ClientResponseBody> support() {
		return KakaoClientResponseBody.class;
	}
}
