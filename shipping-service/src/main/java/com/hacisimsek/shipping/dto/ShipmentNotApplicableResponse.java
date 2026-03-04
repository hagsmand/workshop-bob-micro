package com.hacisimsek.shipping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentNotApplicableResponse {
    private String status;
    private String message;
    private UUID orderId;
}

// Made with Bob
