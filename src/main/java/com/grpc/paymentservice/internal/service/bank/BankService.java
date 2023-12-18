package com.grpc.paymentservice.internal.service.bank;

import com.grpc.paymentservice.external.dto.xbank.OrderResponse;
import com.grpc.paymentservice.external.dto.xbank.request.InquireOrder;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import grpc.paymentservice.PaymentServiceOuterClass;

public interface BankService {
    PaymentResponse makePayment(PaymentServiceOuterClass.CreatePayment createPayment);

    OrderResponse inquireOrder(InquireOrder inquireOrder);
}
