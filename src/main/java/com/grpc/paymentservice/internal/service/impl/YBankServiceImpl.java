package com.grpc.paymentservice.internal.service.impl;

import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.service.BankService;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

@Service
public class YBankServiceImpl implements BankService {
    @Override
    public PaymentResponse makePayment(PaymentServiceOuterClass.CreatePayment createPayment) {
        return null;
    }
}
