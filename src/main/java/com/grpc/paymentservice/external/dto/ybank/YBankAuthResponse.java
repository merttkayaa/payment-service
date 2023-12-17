package com.grpc.paymentservice.external.dto.ybank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YBankAuthResponse {
    private String originalOrderId;
    private String responseCode;
    private String message;
    private boolean isThreeD;
}
