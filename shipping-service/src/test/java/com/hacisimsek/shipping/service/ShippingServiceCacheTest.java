package com.hacisimsek.shipping.service;

import com.hacisimsek.shipping.model.Shipment;
import com.hacisimsek.shipping.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ShippingServiceCacheTest {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private ShipmentRepository shipmentRepository;

    @BeforeEach
    void setUp() {
        // Clear all caches before each test
        cacheManager.getCacheNames().forEach(cacheName -> 
            cacheManager.getCache(cacheName).clear()
        );
    }

    @Test
    void testGetShipmentById_CachesResult() {
        // Arrange
        UUID shipmentId = UUID.randomUUID();
        Shipment shipment = new Shipment();
        shipment.setId(shipmentId);
        shipment.setOrderId(UUID.randomUUID());
        
        when(shipmentRepository.findById(shipmentId))
            .thenReturn(Optional.of(shipment));

        // Act - First call
        Shipment result1 = shippingService.getShipmentById(shipmentId);
        
        // Act - Second call (should hit cache)
        Shipment result2 = shippingService.getShipmentById(shipmentId);

        // Assert
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(shipmentId, result1.getId());
        assertEquals(shipmentId, result2.getId());
        
        // Verify repository was called only once (second call hit cache)
        verify(shipmentRepository, times(1)).findById(shipmentId);
    }

    @Test
    void testGetShipmentByOrderId_CachesResult() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        Shipment shipment = new Shipment();
        shipment.setId(UUID.randomUUID());
        shipment.setOrderId(orderId);
        
        when(shipmentRepository.findByOrderId(orderId))
            .thenReturn(Optional.of(shipment));

        // Act - First call
        Shipment result1 = shippingService.getShipmentByOrderId(orderId);
        
        // Act - Second call (should hit cache)
        Shipment result2 = shippingService.getShipmentByOrderId(orderId);

        // Assert
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(orderId, result1.getOrderId());
        assertEquals(orderId, result2.getOrderId());
        
        // Verify repository was called only once
        verify(shipmentRepository, times(1)).findByOrderId(orderId);
    }
}

// Made with Bob
