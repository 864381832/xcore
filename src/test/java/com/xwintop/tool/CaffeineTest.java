package com.xwintop.tool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

/** 
 * @ClassName: CaffeineTest 
 * @Description: 是基于Java 8的高性能，接近最佳的缓存库
 * @author: xufeng
 * @date: 2017年8月31日 上午1:16:21  
 */
public class CaffeineTest {
	@Test
	public void test() throws Exception {
		Cache<String, String> cache = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).maximumSize(10_000)
				.build();
		String s = cache.get("key", (String t) -> {
			return "1";
		});
		System.out.println(s);
		// Lookup an entry, or null if not found
		System.out.println(cache.getIfPresent("key"));
		// Insert or update an entry
		cache.put("key", "456");
		System.out.println(cache.getIfPresent("key"));
		// Remove an entry
		// cache.invalidate("key");
		Thread.sleep(2000);
		System.out.println(cache.getIfPresent("key"));
	}

	@Test
	public void testLoadingCache() throws Exception {
		LoadingCache<String, String> cache = Caffeine.newBuilder().maximumSize(10_000)
				.expireAfterWrite(5, TimeUnit.SECONDS).refreshAfterWrite(1, TimeUnit.SECONDS).build((String key) -> {
					return null;
				});
		cache.put("key", "123");
		System.out.println(cache.get("key"));
	}

	@Test
	public void testAsyncLoadingCache() throws Exception {
		AsyncLoadingCache<String, String> cache = Caffeine.newBuilder().maximumSize(10_000)
				.expireAfterWrite(10, TimeUnit.MINUTES)
				// Either: Build with a synchronous computation that is wrapped as asynchronous
				.buildAsync((Object key)-> {
						return null;
				});
		// Or: Build with a asynchronous computation that returns a future
//		.buildAsync((Object key, Executor executor)-> {return null;});
		cache.put("key", CompletableFuture.completedFuture("123"));
		System.out.println(cache.get("key").get());
	}
}
