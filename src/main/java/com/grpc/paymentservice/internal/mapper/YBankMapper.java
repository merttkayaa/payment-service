package com.grpc.paymentservice.internal.mapper;

import com.grpc.paymentservice.external.dto.ybank.YBankAuthResponse;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class YBankMapper {
//    YBankMapper INSTANCE = Mappers.getMapper(YBankMapper.class);
    public static final YBankMapper INSTANCE= Mappers.getMapper(YBankMapper.class);
    public  PaymentResponse toPaymentResponse(YBankAuthResponse yBankAuthResponse){
        return PaymentResponse.builder()
                .orderId(yBankAuthResponse.getOriginalOrderId())
                .response(yBankAuthResponse.getResponseCode())
                .message(yBankAuthResponse.getMessage())
                .isThreeD(yBankAuthResponse.isThreeD())
                .build();
    }
}
