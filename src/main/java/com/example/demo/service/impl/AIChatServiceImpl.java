package com.example.demo.service.impl;

import com.example.demo.api.AIChatApi;
import com.example.demo.entity.api.response.AIChatResponse;
import com.example.demo.service.AIChatService;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * impl aichat服务
 *
 * @author Huy Cheung
 * @date 2022/03/08
 */
@Service
public class AIChatServiceImpl implements AIChatService {

    @Override
    public String chat(String msg) {
        if (Strings.isNullOrEmpty(msg)) {
            return "请输入合法字符";
        }
        AIChatResponse reply = AIChatApi.chat(URLEncoder.encode(msg, StandardCharsets.UTF_8));
        String content = reply.getContent();
        return content.replace("{br}", "\\n");
    }
}