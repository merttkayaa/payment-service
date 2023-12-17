package com.grpc.paymentservice.internal.service.impl;

import com.grpc.paymentservice.internal.data.service.PaymentDataService;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.service.factory.BankServiceFactory;
import grpc.paymentservice.PaymentServiceGrpc;
import grpc.paymentservice.PaymentServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.springframework.util.Assert;

import java.util.UUID;

public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase{
    private final BankServiceFactory factory;
    private final PaymentDataService dataService;

    public PaymentServiceImpl(BankServiceFactory factory, PaymentDataService dataService) {
        this.factory = factory;
        this.dataService = dataService;
    }

    @Override
    public void pay(PaymentServiceOuterClass.CreatePayment request, StreamObserver<PaymentServiceOuterClass.PaymentDto> responseObserver){
        validateRequest(request);
        // TODO Gelen isteği dbye kayıt at, orderId yi dön response olarak
        UUID orderId = dataService.createPaymentAndGetId(request);

        PaymentResponse paymentResponse = factory.makePayment(request);

        if("0000".equals(paymentResponse.getResponse())){
            // TODO yukarıda kayıt ettiğin işlemi başarılı olarak güncelle,  gelen orderIdyi tabloya kaydet

            //TODO kuyruğa at  kuyrukta ilgili bankanın inquireOrder fonksşyonunu çağır. Olumsuz olursa dbdeki başarılı kaydı güncelle
            // kuyruga sadece orderId yi at




            // TODO scheduled ile endDate fonksiyonunu kullanarak her gün belli bir saatte tüm transactionları sorgula eğer hata varsa db tablosundaki başarılı kaydı güncelle
            PaymentServiceOuterClass.PaymentDto response = PaymentServiceOuterClass.PaymentDto.newBuilder()
                    .setResponse(paymentResponse.getResponse())
                    .setMessage("Success")
                    .setOrderId(paymentResponse.getOrderId()) // TODO db id dön
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

    private void validateRequest(PaymentServiceOuterClass.CreatePayment request){
        Assert.notNull(request, "request cannot be empty");
        Assert.notNull(request.getAmount(), "amount cannot be empty");
        Assert.notNull(request.getCvv(), "Cvv cannot be empty");
        Assert.notNull(request.getBankId(), "BankId cannot be empty");
        Assert.notNull(request.getCardNumber(), "CardNumber cannot be empty");
        Assert.notNull(request.getExpireDate(), "Date cannot be empty");
        Assert.notNull(request.getFirstName(), "firstName cannot be empty");
        Assert.notNull(request.getLastName(), "lastName cannot be empty");
        Assert.notNull(request.getPaymentMethod(), "payment method must be selected");
    }
}
