package com.xwintop.xcore.commons;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @ClassName: CacheUtil
 * @Description: 缓存工具类
 * @author: xufeng
 * @date: 2018/2/7 11:07
 */
@Getter
@Setter
public class CacheUtil {
    private static CacheUtil cacheUtil = new CacheUtil();
    private Cache cache = Caffeine.newBuilder().expireAfterWrite(12, TimeUnit.HOURS).build();

    public static CacheUtil getInstance() {
        return cacheUtil;
    }

    public void set(Object key, Object value) {
        cache.put(key, value);
    }

    public Object get(Object key) {
        return cache.getIfPresent(key);
    }

    public <T> T get(Object key, Class<T> type) {
        return (T) cache.getIfPresent(key);
    }

    public <T> T get(Object key, Class<T> type, Function mappingFunction) {
        return (T) cache.get(key, mappingFunction);
    }

    public void remove(Object key) {
        cache.invalidate(key);
    }
}
