package com.dhlee.search.blog.client.kakao;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.dhlee.search.blog.client.ClientResponseBody;
import com.dhlee.search.blog.client.kakao.vo.Document;
import com.dhlee.search.blog.client.kakao.vo.Meta;
import com.dhlee.search.blog.domain.BlogEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoClientResponseBody implements ClientResponseBody {
	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("documents")
	private List<Document> documents;

	@Override
	public BlogEntity of() {
		return new BlogEntity(meta.of(), documents.stream().map(Document::of).collect(toList()));
	}
}
