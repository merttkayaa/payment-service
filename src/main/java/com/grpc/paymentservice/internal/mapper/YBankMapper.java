package com.grpc.paymentservice.internal.mapper;

import com.grpc.paymentservice.external.dto.ybank.YBankAuthResponse;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface YBankMapper {
    YBankMapper INSTANCE = Mappers.getMapper(YBankMapper.class);
//    @Mapping(source = "originalOrderId", target = "orderId")
    PaymentResponse toPaymentResponse(YBankAuthResponse yBankAuthResponse);
}
