package com.grpc.paymentservice.internal.data.service;

import com.grpc.paymentservice.external.dto.xbank.OrderResponse;
import com.grpc.paymentservice.internal.data.entity.Payment;
import com.grpc.paymentservice.internal.data.mapper.PaymentMapper;
import com.grpc.paymentservice.internal.data.repository.PaymentRepository;
import com.grpc.paymentservice.internal.dto.PaymentDto;
import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentDataService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public PaymentDataService(PaymentRepository repository, PaymentMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public PaymentDto createPaymentAndGetDto(PaymentServiceOuterClass.CreatePayment createPayment){
       return  mapper.toDto(repository.save(mapper.map(createPayment)));
    }

    public Payment getPaymentById(UUID message) {
        return repository.findById(message)
                .orElseThrow();
        // Global exception yapılınca yap bunu
    }

    public void updateSuccessfulPayment(UUID guid, String orderId) {
        Payment existingEntity = repository.findById(guid).orElseThrow();
        existingEntity.setResponseCode("00");
        existingEntity.setResponseMessage("Success");
        existingEntity.setOrderId(orderId);
        repository.save(existingEntity);
    }

    public void updateFailedPayment(OrderResponse inquireResponse) {
        Payment existingEntity = repository.findByOrderId(inquireResponse.getOriginalOrderId());
        existingEntity.setResponseCode("99");
        existingEntity.setResponseMessage(inquireResponse.getMessage());
        repository.save(existingEntity);
    }
}
