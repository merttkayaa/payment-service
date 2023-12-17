package com.grpc.paymentservice.internal.data.service;

import com.grpc.paymentservice.internal.data.entity.Payment;
import com.grpc.paymentservice.internal.data.mapper.PaymentMapper;
import com.grpc.paymentservice.internal.data.repository.PaymentRepository;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentDataService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public PaymentDataService(PaymentRepository repository, PaymentMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID createPaymentAndGetId(PaymentServiceOuterClass.CreatePayment createPayment){
       return  repository.save(mapper.map(createPayment)).getGuid();
    }

    public Payment getPaymentById(UUID message) {
        return repository.findById(message)
                .orElse(null);
        // Else de throw atmak lazım
        // Global exception yapılınca yap bunu
    }
}
