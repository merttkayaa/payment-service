package com.grpc.paymentservice.internal.producer;

import com.grpc.paymentservice.external.dto.xbank.request.InquireOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentProducer {
    private final  RabbitTemplate rabbitTemplate;
    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(InquireOrder message) {
        rabbitTemplate.convertAndSend("payment", message);
    }
}
