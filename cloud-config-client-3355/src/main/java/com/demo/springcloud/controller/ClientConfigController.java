package com.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ClientConfigController {

    @Value("${config.info}")
    String configinfo;

    @GetMapping("/configInfo")
    public String getConfiginfo() {
        return configinfo;
    }

}
