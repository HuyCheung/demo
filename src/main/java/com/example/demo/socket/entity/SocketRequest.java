package com.example.demo.socket.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Socket请求
 *
 * @author Huy Cheung
 * @date 2022/04/08
 */
@Getter
@Setter
@Accessors(chain = true)
public class SocketRequest {
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

}