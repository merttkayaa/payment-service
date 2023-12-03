package com.grpc.paymentservice.service;

import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.service.BankService;
import com.grpc.paymentservice.internal.service.BankServiceFactory;
import grpc.paymentservice.PaymentServiceGrpc;
import grpc.paymentservice.PaymentServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase{
    private final BankServiceFactory factory;

    public PaymentServiceImpl(BankServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public void pay(PaymentServiceOuterClass.CreatePayment request, StreamObserver<PaymentServiceOuterClass.PaymentDto> responseObserver){
        PaymentResponse paymentResponse = factory.getService(request);
        if("0000".equals(paymentResponse.getResponse())){
            PaymentServiceOuterClass.PaymentDto response = PaymentServiceOuterClass.PaymentDto.newBuilder()
                    .setResponse(paymentResponse.getResponse())
                    .setMessage("Success")
                    .setOrderId(paymentResponse.getOrderId())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else{
            PaymentServiceOuterClass.PaymentDto response = PaymentServiceOuterClass.PaymentDto.newBuilder()
                    .setResponse(paymentResponse.getResponse())
                    .setMessage("Fail")
                    .setOrderId(paymentResponse.getOrderId())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
