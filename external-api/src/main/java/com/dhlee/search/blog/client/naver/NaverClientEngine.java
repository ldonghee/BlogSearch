package com.dhlee.search.blog.client.naver;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dhlee.search.blog.client.ClientEngine;
import com.dhlee.search.blog.client.ClientFrame;
import com.dhlee.search.blog.client.ClientResponseBody;
import com.dhlee.search.blog.client.RequestHeader;
import com.dhlee.search.blog.client.RequestQuery;
import com.dhlee.search.blog.reqeust.SearchRequest;

@Component
public class NaverClientEngine implements ClientEngine {
	private static final String HEADER_CLIENT_ID = "X-Naver-Client-Id";
	private static final String HEADER_CLIENT_SECRET = "X-Naver-Client-Secret";
	private static final String QUERY_KEY = "query";
	private static final String SORT_KEY = "sort";
	private static final String START_KEY = "start";
	private static final String DISPLAY_KEY = "display";
	private static final String SORT_ACCURACY = "accuracy";
	private static final String SORT_SIM = "sim";
	private static final String SORT_DATE = "date";

	@Value("${api.naver.host}")
	private String host;
	@Value("${api.naver.path}")
	private String path;
	@Value("${api.naver.clientId}")
	private String clientId;
	@Value("${api.naver.clientSecretKey}")
	private String clientSecretKey;



	@Override
	public ClientFrame of(SearchRequest searchRequest) {
		return ClientFrame.builder()
						  .host(host)
						  .path(path)
						  .headers(Arrays.asList(
								  new RequestHeader(HEADER_CLIENT_ID, clientId),
								  new RequestHeader(HEADER_CLIENT_SECRET, clientSecretKey)))
						  .params(Arrays.asList(
								  new RequestQuery(QUERY_KEY, searchRequest.getQuery()),
								  new RequestQuery(SORT_KEY, SORT_ACCURACY.equals(searchRequest.getSort()) ? SORT_SIM : SORT_DATE),
								  new RequestQuery(START_KEY, String.valueOf(searchRequest.getPage())),
								  new RequestQuery(DISPLAY_KEY, String.valueOf(searchRequest.getSize()))))
						  .build();
	}

	@Override
	public Class<? extends ClientResponseBody> support() {
		return NaverClientResponseBody.class;
	}
}
