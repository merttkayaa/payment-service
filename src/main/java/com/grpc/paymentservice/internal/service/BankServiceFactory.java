package com.grpc.paymentservice.internal.service;

import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.service.impl.XBankServiceImpl;
import com.grpc.paymentservice.internal.service.impl.YBankServiceImpl;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

@Service
public class BankServiceFactory {
    private final XBankServiceImpl xBankService;
    private final YBankServiceImpl yBankService;


    public BankServiceFactory(XBankServiceImpl xBankService, YBankServiceImpl yBankService) {
        this.xBankService = xBankService;
        this.yBankService = yBankService;
    }

    public PaymentResponse getService(PaymentServiceOuterClass.CreatePayment createPayment) {
        if ("XBank".equals(createPayment.getBankId())) {
            return xBankService.makePayment(createPayment);
        } else if ("YBank".equals(createPayment.getBankId())) {
            return yBankService.makePayment(createPayment);
        }
        throw new IllegalArgumentException("Wrong Bank Id");
    }

}
