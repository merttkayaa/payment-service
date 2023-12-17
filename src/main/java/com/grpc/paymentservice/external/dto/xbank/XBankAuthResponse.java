package com.grpc.paymentservice.external.dto.xbank;
import lombok.Getter;

@Getter
public class XBankAuthResponse {
    private String orderId;
    private String responseCode;
    private String message;
    private boolean isThreeD;
}
