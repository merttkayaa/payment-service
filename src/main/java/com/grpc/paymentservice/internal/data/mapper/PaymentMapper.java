package com.grpc.paymentservice.internal.data.mapper;

import com.grpc.paymentservice.internal.data.entity.Payment;
import com.grpc.paymentservice.internal.dto.PaymentDto;
import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import com.grpc.paymentservice.internal.mapper.XBankMapper;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public abstract class PaymentMapper {
    public static final PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Payment map(PaymentServiceOuterClass.CreatePayment createPayment){
        Payment payment = new Payment();

        payment.setPaymentMethod(PaymentType.fromString(createPayment.getPaymentMethod()));
        payment.setAmount(BigDecimal.valueOf(Long.parseLong(createPayment.getAmount())));
        payment.setCvv(createPayment.getCvv());
        payment.setBankId(createPayment.getBankId());
        payment.setOrderId(createPayment.getOrderId());
        payment.setCardNumber(createPayment.getCardNumber());
        payment.setExpireDate(createPayment.getExpireDate());
        payment.setFirstName(createPayment.getFirstName());
        payment.setLastName(createPayment.getLastName());
        payment.setUserId(createPayment.getUserId());

        return payment;
    }

    public abstract PaymentDto toDto(Payment payment);
}
