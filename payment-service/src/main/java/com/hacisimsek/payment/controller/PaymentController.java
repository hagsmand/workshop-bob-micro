package com.hacisimsek.payment.controller;

import com.hacisimsek.payment.dto.PaymentNotApplicableResponse;
import com.hacisimsek.payment.model.Payment;
import com.hacisimsek.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getPaymentByOrderId(@PathVariable UUID orderId) {
        try {
            Payment payment = paymentService.getPaymentByOrderId(orderId);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            // Payment not found - order was not completed (likely due to inventory failure)
            PaymentNotApplicableResponse response = PaymentNotApplicableResponse.builder()
                    .status("NOT_APPLICABLE")
                    .message("Order was not completed — no payment was processed. This may be due to insufficient inventory stock.")
                    .orderId(orderId)
                    .build();
            return ResponseEntity.ok(response);
        }
    }
}