package com.grpc.paymentservice.internal.dto.enums;

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
}
