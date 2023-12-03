package com.grpc.paymentservice.internal.mapper;

import com.grpc.paymentservice.external.dto.XBankAuthResponse;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    PaymentResponse toPayment(XBankAuthResponse response);
}
