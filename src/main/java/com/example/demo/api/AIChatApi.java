package com.example.demo.api;

import com.ejlchina.okhttps.OkHttps;
import com.example.demo.entity.api.response.AIChatResponse;

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
        return OkHttps.async("http://api.qingyunke.com/api.php")
                .addUrlPara("key", "free")
                .addUrlPara("appid", 0)
                .addUrlPara("msg", msg)
                .get().getResult().getBody()
                .toBean(AIChatResponse.class);
    }
}