package com.example.demo.api;

import com.example.demo.entity.api.AIChatResponse;
import com.example.demo.util.RestUtil;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 人工智能聊天 api
 *
 * @author Huy Cheung
 * @date 2022/03/05
 */
public interface AIChatApi {
    /**
     * 聊天
     *
     * @param msg 必需，关键词，提交前请先经过 urlencode 处理
     * @return {@link String}
     */
    static AIChatResponse chat(String msg) {
        Map<String, Object> query = new HashMap<>(3);
        query.put("key", "free");
        query.put("appid", 0);
        query.put("msg", msg);
        ResponseEntity<AIChatResponse> response = RestUtil.get("http://api.qingyunke.com/api.php", AIChatResponse.class, query);
        return response.getBody();
    }
}