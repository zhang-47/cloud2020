package com.demo.springcloud.service;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "_ok,id=" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHanlder", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "_Timeout,id=" + id;
    }

    public String paymentInfo_TimeoutHanlder(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "_连接超时,/(ㄒoㄒ)/~~";
    }


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_failback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t调用成功,流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_failback(@PathVariable("id") Integer id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "id 不能为负数,请稍后重试:" + df.format(new Date());
    }
}
