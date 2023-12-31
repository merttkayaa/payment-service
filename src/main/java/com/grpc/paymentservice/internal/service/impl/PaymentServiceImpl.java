package com.grpc.paymentservice.internal.service.impl;

import com.grpc.paymentservice.external.dto.xbank.request.InquireOrder;
import com.grpc.paymentservice.internal.data.service.PaymentDataService;
import com.grpc.paymentservice.internal.dto.PaymentDto;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.producer.PaymentProducer;
import com.grpc.paymentservice.internal.service.factory.BankServiceFactory;
import grpc.paymentservice.PaymentServiceGrpc;
import grpc.paymentservice.PaymentServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.springframework.util.Assert;

public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase{
    private final BankServiceFactory factory;
    private final PaymentDataService dataService;
    private final PaymentProducer producer;

    public PaymentServiceImpl(BankServiceFactory factory, PaymentDataService dataService, PaymentProducer producer) {
        this.factory = factory;
        this.dataService = dataService;
        this.producer = producer;
    }

    @Override
    public void pay(PaymentServiceOuterClass.CreatePayment request, StreamObserver<PaymentServiceOuterClass.PaymentDto> responseObserver){
        validateRequest(request);
        PaymentDto paymentDto= dataService.createPaymentAndGetDto(request);

        PaymentResponse paymentResponse = factory.makePayment(request);

        if("0000".equals(paymentResponse.getResponse())){
            dataService.updateSuccessfulPayment(paymentDto.getGuid(),paymentResponse.getOrderId());
            //TODO kuyruğa at  kuyrukta ilgili bankanın inquireOrder fonksşyonunu çağır. Olumsuz olursa dbdeki başarılı kaydı güncelle
            InquireOrder inquireOrder = InquireOrder.builder()
                    .orderId(paymentResponse.getOrderId())
                    .bankId(request.getBankId())
                    .build();

            producer.sendMessage(inquireOrder);


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
