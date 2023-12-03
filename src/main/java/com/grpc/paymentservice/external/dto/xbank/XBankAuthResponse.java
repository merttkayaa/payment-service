package com.grpc.paymentservice.external.dto.xbank;
import lombok.Getter;

@Getter
public class XBankAuthResponse {
    private String orderId;
    private String response;
    private String message;
    private boolean isThreeD;
}
