package com.jpan.togglegateway.bene.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class BeNeRetryAfterCache {

    public static final String BENE_RETRY_AFTER = "beNeReTryAfter";

    private Cache<String, Integer> retryAfterCache;

    public void setRetryAfterCache(final int ttlInSeconds) {
        if (retryAfterCache == null) {
            retryAfterCache = buildCache(ttlInSeconds);
        }

        if (retryAfterCache.getIfPresent(BENE_RETRY_AFTER) == null) {
            retryAfterCache.put(BENE_RETRY_AFTER, ttlInSeconds);
        }
    }

    public Integer getRetryAfterCache() {
        return isPresentRetryAfterCache() ? retryAfterCache.getIfPresent(BENE_RETRY_AFTER) : Integer.valueOf(0);
    }

    public Boolean isPresentRetryAfterCache() {
        if (retryAfterCache != null) {
            final Integer retryAfter = retryAfterCache.getIfPresent(BENE_RETRY_AFTER);
            return retryAfter != null && retryAfter > 0;
        }
        return false;
    }

    private Cache<String, Integer> buildCache(final int ttlInSeconds) {
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(Duration.ofSeconds(ttlInSeconds))
                .build();
    }
}
