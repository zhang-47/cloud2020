package com.demo.springcloud.controller;

import com.demo.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable Integer id) {
        String result = paymentHystrixService.paymentInfo_ok(id);
        log.info("*****result:" + result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFailbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    public String paymentInfo_Timeout(@PathVariable Integer id) {
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("*****result:" + result);
        return result;
    }

//    public String paymentTimeoutFailbackMethod(@PathVariable Integer id) {
//        return "消费者80,对方接口繁忙，或自身出错";
//    }
}
