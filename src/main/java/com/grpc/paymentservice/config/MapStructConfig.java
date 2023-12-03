package com.grpc.paymentservice.config;

import com.grpc.paymentservice.internal.mapper.BankMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {
    @Bean
    public BankMapper bankMapper() {
        return Mappers.getMapper(BankMapper.class);
    }

}