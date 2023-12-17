package com.grpc.paymentservice.external.dto.xbank.request;

import lombok.*;


@Getter
@Builder
public class InquireOrder {
    private String orderId;
    private String bankId;
    private String paymentMethod;
}
