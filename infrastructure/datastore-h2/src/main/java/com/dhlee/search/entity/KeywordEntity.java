package com.dhlee.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class KeywordEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 100)
	private String query;
	private Long count;

	public KeywordEntity() {
	}

	public KeywordEntity(String keyword) {
		this.query = keyword;
		this.count = 1L;
	}

	public KeywordEntity(String keyword, Long count) {
		this.query = keyword;
		this.count = count;
	}

	public void increase() {
		this.count += 1;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public String getQuery() {
		return query;
	}

	public Long getCount() {
		return count;
	}
}
