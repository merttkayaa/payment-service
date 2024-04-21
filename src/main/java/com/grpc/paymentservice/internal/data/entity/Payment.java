package com.grpc.paymentservice.internal.data.entity;

import com.grpc.paymentservice.internal.dto.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payment" ,schema = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
//    @GeneratedValue(generator = "payment_sequence", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID guid;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String cvv;
    private String expireDate;
    private BigDecimal amount;
    private String orderId;
    @Column(name = "bankId", nullable = false)
    private String bankId;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentMethod;
    private String responseCode;
    private String responseMessage;
}
