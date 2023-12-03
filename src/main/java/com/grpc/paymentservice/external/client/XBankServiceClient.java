package com.grpc.paymentservice.external.client;

import com.grpc.paymentservice.external.dto.request.AuthRequest;
import com.grpc.paymentservice.external.dto.request.XBankAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "${feign.client.xbank.name}", url = "${feign.client.xbank.url}")
public interface XBankServiceClient {

    @PostMapping("${feign.client.xbank.path.threeD}")
    XBankAuthResponse payThreeD(AuthRequest request);

    @PostMapping("${feign.client.xbank.path.nonesecure}")
    XBankAuthResponse payNonSecure(AuthRequest request);

}
