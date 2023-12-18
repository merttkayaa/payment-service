package com.grpc.paymentservice.internal.dto;

import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    private UUID guid;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String cvv;
    private String expireDate;
    private BigDecimal amount;
    private String orderId;
    private String bankId;
    private Long userId;
    private PaymentType paymentMethod;
    private String responseCode;
    private String responseMessage;
}
