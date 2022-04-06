package com.example.demo.controller;

import com.example.demo.service.AIChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("ai")
public class AIChatController {
    @Resource
    private AIChatService aiChatService;

    @GetMapping("chat")
    public String chat(String msg) {
        return aiChatService.chat(msg);
    }
}