package com.grpc.paymentservice.internal.dto.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentType {
    NONSECURE("NONSECURE"),
    THREED("THREED");


    private final String name;

    PaymentType(String description) {
        this.name = description;
    }

    public String getDescription() {
        return name;
    }

    public static PaymentType fromString(String text) {
        Optional<PaymentType> matchingType = Arrays.stream(PaymentType.values())
                .filter(b -> b.name.equalsIgnoreCase(text))
                .findFirst();

        if (matchingType.isPresent()) {
            return matchingType.get();
        } else {
            throw new IllegalArgumentException("Unknown payment method: " + text);
        }
    }
}
