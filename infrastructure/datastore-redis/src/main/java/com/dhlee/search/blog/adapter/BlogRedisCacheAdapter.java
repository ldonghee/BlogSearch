package com.dhlee.search.blog.adapter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.dhlee.search.blog.domain.Blog;
import com.dhlee.search.blog.domain.BlogRedisCacheKey;
import com.dhlee.search.blog.port.LoadBlogCachePort;
import com.dhlee.search.blog.port.SaveBlogCachePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogRedisCacheAdapter implements LoadBlogCachePort, SaveBlogCachePort {
	private static final String KEY_FORMAT = "%s_%s_%s_%s";
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	@Transactional(readOnly = true)
	public List<Blog> findBlogs(String keyword, Integer page, Integer size, String sort) {
		String key = String.format(KEY_FORMAT, keyword, page, size, sort);
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		String cachedBlogsJson = hashOperations.get(BlogRedisCacheKey.BLOG_LIST.name(), key);

		if (Objects.isNull(cachedBlogsJson)) {
			return Collections.emptyList();
		}

		try {
			return objectMapper.readValue(cachedBlogsJson, new TypeReference<List<Blog>>() {});
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException("JsonMappingException Exception : ", e.getCause());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("JsonProcessingException Exception : ", e.getCause());
		}
	}

	@Override
	public void saveBlogs(List<Blog> blogs, String keyword, Integer page, Integer size, String sort) {
		String key = String.format(KEY_FORMAT, keyword, page, size, sort);
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

		try {
			String blogsJson = objectMapper.writeValueAsString(blogs);
			hashOperations.put(BlogRedisCacheKey.BLOG_LIST.name(), key, blogsJson);
			redisTemplate.expire(key, 10, TimeUnit.SECONDS);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("JsonProcessingException Exception : ", e.getCause());
		}
	}
}
