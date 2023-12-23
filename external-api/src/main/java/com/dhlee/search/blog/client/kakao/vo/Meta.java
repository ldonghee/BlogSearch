package com.dhlee.search.blog.client.kakao.vo;

import lombok.Getter;

import com.dhlee.search.blog.domain.BlogMetaEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class Meta {
	@JsonProperty("total_count")
	private Integer totalCount;
	@JsonProperty("pageable_count")
	private Integer pageableCount;
	@JsonProperty("is_end")
	private Boolean isEnd;

	public BlogMetaEntity of() {
		return BlogMetaEntity.builder()
							 .totalCount(totalCount)
							 .pageableCount(pageableCount)
							 .isEnd(isEnd)
							 .build();
	}
}
