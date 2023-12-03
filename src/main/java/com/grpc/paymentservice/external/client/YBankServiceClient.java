package com.grpc.paymentservice.external.client;


import com.grpc.paymentservice.external.dto.ybank.YBankAuthResponse;
import com.grpc.paymentservice.external.dto.ybank.request.PayRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${feign.client.ybank.name}", url = "${feign.client.ybank.url}")
public interface YBankServiceClient {
    @PostMapping("${feign.client.ybank.path.threeD}")
    YBankAuthResponse payThreeD(PayRequest request);

    @PostMapping("${feign.client.ybank.path.nonesecure}")
    YBankAuthResponse payNonSecure(PayRequest request);
}
