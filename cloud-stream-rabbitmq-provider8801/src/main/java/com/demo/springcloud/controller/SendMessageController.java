package com.demo.springcloud.controller;

import com.demo.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String send() {
        return messageProvider.send();
    }
}
