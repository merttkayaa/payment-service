package com.grpc.paymentservice.internal.service.impl;

import com.grpc.paymentservice.external.client.XBankServiceClient;
import com.grpc.paymentservice.external.dto.request.AuthRequest;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import com.grpc.paymentservice.internal.mapper.BankMapper;
import com.grpc.paymentservice.internal.service.BankService;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

@Service
public class XBankServiceImpl implements BankService {
    private final XBankServiceClient client;
    private final BankMapper mapper;

    public XBankServiceImpl(XBankServiceClient client, BankMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public PaymentResponse makePayment(PaymentServiceOuterClass.CreatePayment createPayment) {
        boolean isThreeD = !PaymentType.NONSECURE.getDescription().equals(createPayment.getPaymentMethod());

        AuthRequest request = AuthRequest.builder()
                .cardNumber(createPayment.getCardNumber())
                .cvv(createPayment.getCvv())
                .isThreeD(isThreeD)
                .expireDate(createPayment.getExpireDate())
                .build();

        if(isThreeD){
            return mapper.toPayment(client.payThreeD(request));
        }else{
           return  mapper.toPayment(client.payNonSecure(request));
        }
    }
}
