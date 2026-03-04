package com.hacisimsek.shipping.controller;

import com.hacisimsek.shipping.dto.ShipmentNotApplicableResponse;
import com.hacisimsek.shipping.model.Shipment;
import com.hacisimsek.shipping.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable UUID shipmentId) {
        return ResponseEntity.ok(shippingService.getShipmentById(shipmentId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getShipmentByOrderId(@PathVariable UUID orderId) {
        try {
            Shipment shipment = shippingService.getShipmentByOrderId(orderId);
            return ResponseEntity.ok(shipment);
        } catch (RuntimeException e) {
            // Shipment not found - order was not completed (likely due to inventory or payment failure)
            ShipmentNotApplicableResponse response = ShipmentNotApplicableResponse.builder()
                    .status("NOT_APPLICABLE")
                    .message("Order was not completed — no shipment was created. This may be due to insufficient inventory stock or payment failure.")
                    .orderId(orderId)
                    .build();
            return ResponseEntity.ok(response);
        }
    }
}