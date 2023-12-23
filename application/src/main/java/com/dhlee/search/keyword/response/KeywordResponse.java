package com.dhlee.search.keyword.response;

import java.util.List;

import com.dhlee.search.keyword.domain.Keyword;

public class KeywordResponse {
	private final List<Keyword> keywords;
	public KeywordResponse(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	public List<Keyword> getKeywords() {
		return keywords;
	}
}
