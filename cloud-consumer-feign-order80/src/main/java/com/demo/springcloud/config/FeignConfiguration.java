package com.demo.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

}
