package com.grpc.paymentservice.internal.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String orderId;
    private String responseCode;
    private String message;
    private boolean isThreeD;

}
