package com.hacisimsek.shipping.controller;

import com.hacisimsek.shipping.config.CacheConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shipping/cache")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CacheController {

    private final CacheManager cacheManager;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get shipments cache stats
        Cache shipmentsCache = cacheManager.getCache(CacheConfig.SHIPMENT_CACHE);
        if (shipmentsCache instanceof ConcurrentMapCache) {
            ConcurrentMapCache concurrentCache = (ConcurrentMapCache) shipmentsCache;
            stats.put("shipmentsCache", Map.of(
                "name", CacheConfig.SHIPMENT_CACHE,
                "size", concurrentCache.getNativeCache().size(),
                "keys", concurrentCache.getNativeCache().keySet()
            ));
        }
        
        // Get shipments by order cache stats
        Cache shipmentsByOrderCache = cacheManager.getCache(CacheConfig.SHIPMENT_BY_ORDER_CACHE);
        if (shipmentsByOrderCache instanceof ConcurrentMapCache) {
            ConcurrentMapCache concurrentCache = (ConcurrentMapCache) shipmentsByOrderCache;
            stats.put("shipmentsByOrderCache", Map.of(
                "name", CacheConfig.SHIPMENT_BY_ORDER_CACHE,
                "size", concurrentCache.getNativeCache().size(),
                "keys", concurrentCache.getNativeCache().keySet()
            ));
        }
        
        return ResponseEntity.ok(stats);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, String>> clearAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
        
        return ResponseEntity.ok(Map.of(
            "message", "All caches cleared successfully",
            "timestamp", String.valueOf(System.currentTimeMillis())
        ));
    }

    @DeleteMapping("/clear/{cacheName}")
    public ResponseEntity<Map<String, String>> clearCache(@PathVariable String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
            return ResponseEntity.ok(Map.of(
                "message", "Cache '" + cacheName + "' cleared successfully",
                "timestamp", String.valueOf(System.currentTimeMillis())
            ));
        }
        
        return ResponseEntity.notFound().build();
    }
}

// Made with Bob
