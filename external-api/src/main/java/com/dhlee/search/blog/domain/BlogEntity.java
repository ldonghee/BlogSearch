package com.dhlee.search.blog.domain;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogEntity {
	private final BlogMetaEntity blogMetaEntity;
	private final List<BlogDocumentEntity> blogEntities;
}
