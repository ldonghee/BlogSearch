package com.dhlee.search.blog.request;

public class BlogRequest {
	private static final String DEFAULT_SORT = "accuracy";
	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_SIZE = 1;
	private String keyword;
	private String sort;
	private int page;
	private int size;

	public BlogRequest(String keyword, String sort, int page, int size) {
		this.keyword = keyword;
		this.sort = sort;
		this.page = page;
		this.size = size;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getSort() {
		return sort;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}
}
