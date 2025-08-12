package com.bashverse.quicklink.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Defines the cache manager for the application.
     * Using ConcurrentMapCacheManager for simplicity (stores cache in memory).
     *
     * In production, you can replace with Redis, Caffeine, or Ehcache.
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("urlMappings");
    }
}
