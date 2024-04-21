package com.grpc.paymentservice.internal.mapper;

import com.grpc.paymentservice.external.dto.xbank.XBankAuthResponse;
import com.grpc.paymentservice.internal.data.entity.Payment;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public abstract class XBankMapper {

    public static final XBankMapper INSTANCE = Mappers.getMapper(XBankMapper.class);

    public  PaymentResponse toPayment(XBankAuthResponse response){
        return PaymentResponse.builder()
                .responseCode(response.getResponseCode())
                .message(response.getMessage())
                .orderId(response.getOrderId())
                .isThreeD(response.isThreeD())
                .build();
    }
}
