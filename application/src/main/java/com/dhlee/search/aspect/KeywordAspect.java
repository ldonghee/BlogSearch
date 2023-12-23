package com.dhlee.search.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.dhlee.search.keyword.port.SaveKeywordServicePort;

@Aspect
@Component
public class KeywordAspect {
	private SaveKeywordServicePort saveKeywordServicePort;

	public KeywordAspect(SaveKeywordServicePort saveKeywordServicePort) {
		this.saveKeywordServicePort = saveKeywordServicePort;
	}


	@Around(value = "execution(* com.dhlee.search.blog.serviceadapter.BlogServiceAdapter.getBlogs(..)) && args(query, ..)")
	public Object aroundSearch(ProceedingJoinPoint pjp, String query) throws Throwable {
		saveKeywordServicePort.saveKeyword(query);
		return pjp.proceed();
	}
}
