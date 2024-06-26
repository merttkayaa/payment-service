package com.grpc.paymentservice.internal.consumer;


import com.grpc.paymentservice.external.dto.xbank.OrderResponse;
import com.grpc.paymentservice.external.dto.xbank.request.InquireOrder;
import com.grpc.paymentservice.internal.data.entity.Payment;
import com.grpc.paymentservice.internal.data.service.PaymentDataService;
import com.grpc.paymentservice.internal.service.factory.BankServiceFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentConsumer {
    private final PaymentDataService dataService;
    private final BankServiceFactory factory;


    public PaymentConsumer(PaymentDataService dataService, BankServiceFactory factory) {
        this.dataService = dataService;
        this.factory = factory;
    }

    @RabbitListener(queues = "payment")
    public void consumeMessage(InquireOrder message) {
        OrderResponse inquireResponse = factory.inquireOrder(message);
        if(!"00".equals(inquireResponse.getResponseCode()))
            dataService.updateFailedPayment(inquireResponse);
    }
}
