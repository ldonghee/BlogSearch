package com.dhlee.search.keyword.domain;

public class Keyword {
	private String query;
	private Long count;

	public Keyword(String query, Long count) {
		this.query = query;
		this.count = count;
	}

	public Keyword(String query) {
		this.query = query;
		this.count = 1L;
	}

	public String getQuery() {
		return query;
	}

	public Long getCount() {
		return count;
	}
}
