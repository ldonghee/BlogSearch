package com.dhlee.search.keyword.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhlee.search.keyword.domain.Keyword;
import com.dhlee.search.keyword.port.LoadKeywordServicePort;
import com.dhlee.search.keyword.response.KeywordResponse;

@RestController
@RequestMapping(value = "/keyword")
public class KeywordController {
	private final LoadKeywordServicePort keywordServicePort;

	public KeywordController(LoadKeywordServicePort keywordServicePort) {
		this.keywordServicePort = keywordServicePort;
	}

	@GetMapping("/popular")
	public ResponseEntity<KeywordResponse> getPopularKeywords() {
		List<Keyword> popularKeywords = keywordServicePort.getPopularKeywords();
		return ResponseEntity.ok(new KeywordResponse(popularKeywords));
	}
}
