package com.dhlee.search.keyword.domain;

public class Keyword {
	private String query;
	private Long count;

	public Keyword(String query, Long count) {
		this.query = query;
		this.count = count;
	}

	public String getQuery() {
		return query;
	}

	public Long getCount() {
		return count;
	}
}
