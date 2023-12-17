package com.grpc.paymentservice.internal.service.factory;

import com.grpc.paymentservice.external.dto.xbank.request.InquireOrder;
import com.grpc.paymentservice.internal.dto.PaymentResponse;
import com.grpc.paymentservice.internal.service.bank.BankService;
import com.grpc.paymentservice.internal.service.bank.impl.XBankServiceImpl;
import com.grpc.paymentservice.internal.service.bank.impl.YBankServiceImpl;
import grpc.paymentservice.PaymentServiceOuterClass;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BankServiceFactory {
    private final ApplicationContext context;

    public BankServiceFactory(ApplicationContext context) {
        this.context = context;
    }

    public PaymentResponse makePayment(PaymentServiceOuterClass.CreatePayment createPayment) {
        BankService service =  getServiceBasedOnBank(createPayment.getBankId());
        return service.makePayment(createPayment);
    }
    public void inquireOrder(InquireOrder inquireOrder){
        BankService service =  getServiceBasedOnBank(inquireOrder.getBankId());
        // TODO her banka için feignclient isteklerini hazırla
        service.inquireOrder(inquireOrder);
    }

    private BankService getServiceBasedOnBank(String bankId) {
        if ("XBank".equals(bankId)) {
            return context.getBean(XBankServiceImpl.class);
        } else if ("YBank".equals(bankId)) {
            return context.getBean(YBankServiceImpl.class);
        }
        //TODO Hatayı handle et
        throw new IllegalArgumentException("Wrong Bank Id");
    }

}
