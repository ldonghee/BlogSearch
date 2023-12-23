package com.dhlee.search.blog.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.search.blog.domain.BlogEntity;
import com.dhlee.search.blog.reqeust.SearchRequest;

import reactor.core.publisher.Mono;

public interface ClientEngine {
	default Mono<BlogEntity> search(SearchRequest searchRequest) {
		ClientFrame frame = of(searchRequest);
		return build(frame);
	}

	default Mono<BlogEntity> build(ClientFrame request) {
		return  WebClient.builder()
						 .baseUrl(request.getHost()).build().get()
						 .uri(uriBuilder -> uriBuilder.path(request.getPath()).queryParams(request.getParams()).build())
						 .headers(header -> request.getHeaders().forEach(each -> header.add(each.getHeaderKey(), each.getHeaderValue())))
						 .retrieve()
						 .onStatus(HttpStatus::is4xxClientError, res -> res.bodyToMono(String.class).map(IllegalStateException::new))
						 .onStatus(HttpStatus::is5xxServerError, res -> res.bodyToMono(String.class).map(IllegalStateException::new))
						 .bodyToMono(support())
						 .map(ClientResponseBody::of);
	}

	ClientFrame of(SearchRequest searchRequest);

	Class<? extends ClientResponseBody> support();
}
