package com.grpc.paymentservice.internal.data.mapper;

import com.grpc.paymentservice.internal.data.entity.Payment;
import com.grpc.paymentservice.internal.mapper.XBankMapper;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment map(PaymentServiceOuterClass.CreatePayment createPayment);
}
