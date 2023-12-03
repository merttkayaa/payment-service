package com.grpc.paymentservice.external.dto.request;
import lombok.Getter;

@Getter
public class XBankAuthResponse {
    private String orderId;
    private String response;
    private String message;
    private boolean isThreeD;
}
