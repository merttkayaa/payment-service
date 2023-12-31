package com.grpc.paymentservice.internal.producer;

import com.grpc.paymentservice.external.dto.xbank.request.InquireOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {
    private final  RabbitTemplate rabbitTemplate;
    private final String routingKey;
    private final String exchangeName;

    public PaymentProducer(RabbitTemplate rabbitTemplate,
                           @Value("${spring.rabbit.routing.key") String routingKey,  @Value("${spring.rabbit.exchange.name")String exchangeName) {
        this.rabbitTemplate = rabbitTemplate;
        this.routingKey = routingKey;
        this.exchangeName = exchangeName;
    }

    public void sendMessage(InquireOrder message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
