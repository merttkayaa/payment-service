package com.grpc.paymentservice.config;

import com.grpc.paymentservice.internal.mapper.XBankMapper;
import com.grpc.paymentservice.internal.mapper.YBankMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {
    @Bean
    public XBankMapper xBankMapper() {
        return Mappers.getMapper(XBankMapper.class);
    }

    @Bean
    public YBankMapper yBankMapper() {
        return Mappers.getMapper(YBankMapper.class);
    }

}