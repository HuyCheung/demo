package com.example.demo.entity.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIChatResponse {
    /**
     * 响应结果码
     */
    private String result;
    /**
     * 内容
     */
    private String content;
}