package com.grpc.paymentservice.internal.service.bank.impl;

import com.grpc.paymentservice.external.client.XBankServiceClient;
import com.grpc.paymentservice.external.dto.xbank.request.AuthRequest;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import com.grpc.paymentservice.internal.mapper.XBankMapper;
import com.grpc.paymentservice.internal.service.bank.BankService;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class XBankServiceImpl implements BankService {
    private final XBankServiceClient client;
    private final XBankMapper mapper;

    public XBankServiceImpl(XBankServiceClient client, XBankMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public PaymentResponse makePayment(PaymentServiceOuterClass.CreatePayment createPayment) {
        boolean isThreeD = !PaymentType.NONSECURE.getDescription().equals(createPayment.getPaymentMethod());

        AuthRequest request = AuthRequest.builder()
                .firstName(createPayment.getFirstName())
                .lastName(createPayment.getLastName())
                .cardNumber(createPayment.getCardNumber())
                .cvv(createPayment.getCvv())
                .isThreeD(isThreeD)
                .expireDate(createPayment.getExpireDate())
                .amount(BigDecimal.valueOf(Long.parseLong(createPayment.getAmount())))
                .build();

        if(isThreeD){
            return mapper.toPayment(client.payThreeD(request));
        }else{
           return  mapper.toPayment(client.payNonSecure(request));
        }
    }
}
