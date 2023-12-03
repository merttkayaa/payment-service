package com.grpc.paymentservice.external.dto.ybank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YBankAuthResponse {
    private String originalOrderId;
    private String response;
    private String message;
    private boolean isThreeD;
}
