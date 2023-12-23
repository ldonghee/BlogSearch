package com.dhlee.search.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhlee.search.entity.KeywordEntity;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
	List<KeywordEntity> findTop10ByOrderByCountDesc();
	Optional<KeywordEntity> findByQuery(String query);
}
