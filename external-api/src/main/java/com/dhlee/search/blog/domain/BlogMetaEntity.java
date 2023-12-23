package com.dhlee.search.blog.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogMetaEntity {
	private Integer totalCount;
	private Integer pageableCount;
	private Boolean isEnd;
}
