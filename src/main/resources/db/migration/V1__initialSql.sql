CREATE SCHEMA payment;


create table payment.payment
(
    guid               UUID PRIMARY KEY,
    first_name             varchar(10)      not null,
    last_name              varchar(10)      not null,
    card_number            varchar(10)      not null,
    cvv                   varchar(10)      not null,
    expire_date            varchar(10)      not null,
    amount                NUMERIC(15, 2),
    order_id               varchar(10)      not null,
    bank_id                varchar(100)     not null,
    user_id                bigint     not null,
    payment_method         varchar(10)      not null,
    response_code          varchar(20),
    response_message          varchar(20)
);

-- create sequence payment.payment_sequence
--     START 1
--     INCREMENT 1
--     minvalue 1
--     maxvalue 999999
--     CACHE 1;