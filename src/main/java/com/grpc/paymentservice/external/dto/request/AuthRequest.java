package com.grpc.paymentservice.external.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {
    private String cardNumber;
    private String cvv;
    private String expireDate;
    private Boolean isThreeD;
}
