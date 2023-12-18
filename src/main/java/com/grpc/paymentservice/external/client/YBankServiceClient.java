package com.grpc.paymentservice.external.client;


import com.grpc.paymentservice.external.dto.xbank.OrderResponse;
import com.grpc.paymentservice.external.dto.ybank.YBankAuthResponse;
import com.grpc.paymentservice.external.dto.ybank.request.PayRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.client.ybank.name}", url = "${feign.client.ybank.url}")
public interface YBankServiceClient {
    @PostMapping("${feign.client.ybank.path.threeD}")
    YBankAuthResponse payThreeD(@RequestBody PayRequest request);

    @PostMapping("${feign.client.ybank.path.nonesecure}")
    YBankAuthResponse payNonSecure(@RequestBody PayRequest request);

    @GetMapping("${feign.client.xbank.path.inquireorder}")
    OrderResponse inquireOrder(@RequestParam String inquireOrder);
}
