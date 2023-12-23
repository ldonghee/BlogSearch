package com.dhlee.search;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.dhlee.search.blog.controller.BlogController;
import com.dhlee.search.blog.request.BlogRequest;
import com.dhlee.search.keyword.controller.KeywordController;
import com.dhlee.search.keyword.domain.Keyword;
import com.dhlee.search.model.CommonResponse;

@SpringBootTest
public class ConcurrencyTest {
	private static final int THREAD_COUNT = 50;
	private static final ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
	@Autowired
	private BlogController blogController;
	@Autowired
	private KeywordController keywordController;

	@Test
	@DisplayName("50번의 검색어 동시 접근 시, 횟수 증가 테스트")
	public void concurrency() throws InterruptedException, BrokenBarrierException {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT + 1);

		// when
		CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
		for (int i=0; i < THREAD_COUNT; i++) {
			service.execute(() -> {
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				blogController.getBlogs(new BlogRequest("TEST", "accuracy", 1, 1));
				latch.countDown();
			});
		}

		cyclicBarrier.await();
		latch.await();

		// then
		ResponseEntity<CommonResponse<List<Keyword>>> popularKeywords = keywordController.getPopularKeywords();
		Long actual = popularKeywords.getBody().getResult().get(0).getCount();
		assertThat(actual).isEqualTo(THREAD_COUNT);
	}
}
