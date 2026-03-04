package com.hacisimsek.shipping.controller;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/shipping/cache")
@CrossOrigin(origins = "*")
public class CacheController {

    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        Collection<String> cacheNames = cacheManager.getCacheNames();
        
        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache instanceof ConcurrentMapCache) {
                ConcurrentMapCache concurrentCache = (ConcurrentMapCache) cache;
                Map<String, Object> cacheInfo = new HashMap<>();
                
                Set<Object> keys = concurrentCache.getNativeCache().keySet();
                cacheInfo.put("size", keys.size());
                cacheInfo.put("keys", keys);
                
                stats.put(cacheName, cacheInfo);
            }
        }
        
        return ResponseEntity.ok(stats);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, String>> clearAllCaches() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "All caches cleared successfully");
        response.put("clearedCaches", String.join(", ", cacheNames));
        
        return ResponseEntity.ok(response);
    }
}

// Made with Bob
