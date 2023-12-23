package com.dhlee.search.blog.client;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.dhlee.search.blog.client.kakao.KakaoClientEngine;
import com.dhlee.search.blog.client.naver.NaverClientEngine;
import com.dhlee.search.blog.domain.BlogEntity;
import com.dhlee.search.blog.reqeust.SearchRequest;

@Slf4j
@Component
public class ClientEnginManager {
	private final ClientEngine KakaoClientEngine;
	private final ClientEngine NaverClientEngine;

	public ClientEnginManager() {
		KakaoClientEngine = new KakaoClientEngine();
		NaverClientEngine = new NaverClientEngine();
	}

	public BlogEntity search(SearchRequest searchRequest) {
		try {
			return KakaoClientEngine.search(searchRequest).block();
		} catch (Exception e) {
			return NaverClientEngine.search(searchRequest).block();
		}
	}
}
