package com.dhlee.search.blog.domain;

public class Blog {
	private String title;
	private String url;
	private String contents;
	private String blogName;
	private String datetime;

	public Blog() {
	}

	public Blog(String title, String url, String contents, String blogName, String datetime) {
		this.title = title;
		this.url = url;
		this.contents = contents;
		this.blogName = blogName;
		this.datetime = datetime;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getContents() {
		return contents;
	}

	public String getBlogName() {
		return blogName;
	}

	public String getDatetime() {
		return datetime;
	}
}
