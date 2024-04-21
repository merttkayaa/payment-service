package com.grpc.paymentservice.config;

import com.grpc.paymentservice.internal.service.impl.PaymentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class GrpcServerConfig {

        @Bean
        public Server grpcServer(PaymentServiceImpl paymentService) {
            return ServerBuilder
                    .forPort(9091) // Dinlenecek port
                    .addService(paymentService) // Spring tarafından yönetilen PaymentServiceImpl instance'ı
                    .build();
        }
    }
