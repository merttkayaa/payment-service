package com.grpc.paymentservice.internal.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentProducer {
    private final  RabbitTemplate rabbitTemplate;
    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(UUID message) {
        rabbitTemplate.convertAndSend("payment", message);
    }
}
