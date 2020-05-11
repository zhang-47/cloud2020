package com.demo.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "80_paymentInfo_ok_error";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "80_paymentInfo_Timeout_error";
    }
}
