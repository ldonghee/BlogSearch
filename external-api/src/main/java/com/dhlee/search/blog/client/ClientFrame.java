package com.dhlee.search.blog.client;

import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ClientFrame {
	private final String host;
	private final String path;
	private final List<RequestHeader> headers;
	private final List<RequestQuery> params;
	private final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

	public MultiValueMap<String, String> getParams() {
		params.forEach(param -> multiValueMap.add(param.getQuery(), param.getValue()));
		return multiValueMap;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}

	public List<RequestHeader> getHeaders() {
		return headers;
	}
}
