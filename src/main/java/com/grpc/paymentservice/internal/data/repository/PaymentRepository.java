package com.grpc.paymentservice.internal.data.repository;

import com.grpc.paymentservice.internal.data.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment,UUID> {
    Payment findByOrderId(String orderId);
}
