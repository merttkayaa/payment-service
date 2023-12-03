package com.grpc.paymentservice.internal.service.impl;


import com.grpc.paymentservice.external.client.YBankServiceClient;
import com.grpc.paymentservice.external.dto.ybank.request.PayRequest;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import com.grpc.paymentservice.internal.mapper.YBankMapper;
import com.grpc.paymentservice.internal.service.BankService;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class YBankServiceImpl implements BankService {

    private final YBankServiceClient client;
    private final YBankMapper mapper;

    public YBankServiceImpl(YBankServiceClient client, YBankMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }
    @Override
    public PaymentResponse makePayment(PaymentServiceOuterClass.CreatePayment createPayment) {
        boolean isThreeD = !PaymentType.NONSECURE.getDescription().equals(createPayment.getPaymentMethod());

        PayRequest request = PayRequest.builder()
                .firstName(createPayment.getFirstName())
                .lastName(createPayment.getLastName())
                .cardNumber(createPayment.getCardNumber())
                .cvv(createPayment.getCvv())
                .isThreeD(isThreeD)
                .expireDate(createPayment.getExpireDate())
                .amount(BigDecimal.valueOf(Long.parseLong(createPayment.getAmount())))
                .build();

//        if(isThreeD){
//            return mapper.toPayment(client.payThreeD(request));
//        }else{
//            return  mapper.toPayment(client.payNonSecure(request));
//        }

        return null;

    }
}
