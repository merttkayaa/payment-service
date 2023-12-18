package com.grpc.paymentservice.external.client;

import com.grpc.paymentservice.external.dto.xbank.OrderResponse;
import com.grpc.paymentservice.external.dto.xbank.request.AuthRequest;
import com.grpc.paymentservice.external.dto.xbank.XBankAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "${feign.client.xbank.name}", url = "${feign.client.xbank.url}")
public interface XBankServiceClient {

    @PostMapping("${feign.client.xbank.path.threeD}")
    XBankAuthResponse payThreeD(@RequestBody AuthRequest request);

    @PostMapping("${feign.client.xbank.path.nonesecure}")
    XBankAuthResponse payNonSecure(@RequestBody AuthRequest request);

    @GetMapping("${feign.client.xbank.path.inquireorder}")
    OrderResponse inquireOrder(@RequestParam String inquireOrder);


}
