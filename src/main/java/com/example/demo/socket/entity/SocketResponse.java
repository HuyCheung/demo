package com.example.demo.socket.entity;

import com.ejlchina.json.JSONKit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Socket响应
 *
 * @author Huy Cheung
 * @date 2022/04/08
 */
@Setter
@Getter
public class SocketResponse {
    /**
     * 发送者
     */
    private String formUserId;
    /**
     * 接收者
     */
    private String toUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * msg类型
     */
    private String msgType;
    /**
     * 内容
     */
    private String content;

    @Override
    public String toString() {
        return JSONKit.toJson(this);
    }
}