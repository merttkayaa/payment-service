CREATE SCHEMA payment;


create table payment.payment
(
    guid               UUID PRIMARY KEY,
    firstName             varchar(10)      not null,
    lastName              varchar(10)      not null,
    cardNumber            varchar(10)      not null,
    cvv                   varchar(10)      not null,
    expireDate            varchar(10)      not null,
    amount                NUMERIC(15, 2),
    orderId               varchar(10)      not null,
    bankId                varchar(100)     not null,
    userId                bigint     not null,
    paymentMethod         varchar(10)      not null,
    responseCode          varchar(20),
    responseMessage          varchar(20)
);

create sequence payment.payment_sequence
    START 1
    INCREMENT 1
    minvalue 1
    maxvalue 999999
    CACHE 1;