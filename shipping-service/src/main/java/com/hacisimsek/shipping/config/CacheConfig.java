package com.hacisimsek.shipping.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String SHIPMENT_CACHE = "shipments";
    public static final String SHIPMENT_BY_ORDER_CACHE = "shipmentsByOrder";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache(SHIPMENT_CACHE),
                new ConcurrentMapCache(SHIPMENT_BY_ORDER_CACHE)
        ));
        return cacheManager;
    }
}

// Made with Bob
