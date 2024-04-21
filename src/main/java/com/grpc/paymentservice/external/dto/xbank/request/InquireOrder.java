package com.grpc.paymentservice.external.dto.xbank.request;

import lombok.*;

import java.io.Serializable;


@Getter
@Builder
public class InquireOrder implements Serializable {
    private String orderId;
    private String bankId;
}
