package com.dhlee.search.blog.client.naver;

import static java.util.stream.Collectors.toList;

import java.util.List;

import lombok.Getter;

import com.dhlee.search.blog.client.ClientResponseBody;
import com.dhlee.search.blog.client.naver.vo.Item;
import com.dhlee.search.blog.domain.BlogEntity;
import com.dhlee.search.blog.domain.BlogMetaEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class NaverClientResponseBody implements ClientResponseBody {
	@JsonProperty("total")
	private Integer totalCount;

	@JsonProperty("display")
	private Integer pageableCount;

	@JsonProperty("items")
	private List<Item> items;

	@Override
	public BlogEntity of() {
		BlogMetaEntity meta = BlogMetaEntity.builder()
											 .totalCount(totalCount)
											 .pageableCount(pageableCount)
											 .isEnd(false)
											 .build();

		return new BlogEntity(meta, items.stream().map(Item::of).collect(toList()));
	}
}
