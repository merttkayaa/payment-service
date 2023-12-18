package com.grpc.paymentservice.external.dto.xbank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private String orderId;
    private String amount;
    private String responseCode;
    private String paymentType;
    private String message;
    private String originalOrderId;
}
