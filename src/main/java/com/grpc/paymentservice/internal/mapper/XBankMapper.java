package com.grpc.paymentservice.internal.mapper;

import com.grpc.paymentservice.external.dto.xbank.XBankAuthResponse;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface XBankMapper {

    XBankMapper INSTANCE = Mappers.getMapper(XBankMapper.class);

    PaymentResponse toPayment(XBankAuthResponse response);
}
