package com.example.demo.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * websocket服务
 *
 * @author Huy Cheung
 * @date 2022/04/07
 */
@Component
@ServerEndpoint("/ws/{sessionId}")
public class WebSocketEndpoint {

    /**
     * 连接成功
     * <p>
     * 当 websocket 建立连接成功后会触发这个注解修饰的方法
     *
     * @param session   会话
     * @param sessionId 会话id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sessionId") String sessionId) {
        System.out.println(sessionId + "连接成功");
    }

    /**
     * 连接关闭
     * <p>
     * 当 websocket 建立的连接断开后会触发这个注解修饰的方法
     *
     * @param session 会话
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     * <p>
     * 当客户端发送消息到服务端时，会触发这个注解修改的方法
     *
     * @param message 消息
     * @return {@link String}
     */
    @OnMessage
    public String onMessage(String message) throws IOException {
        return "servet 发送：" + message;
    }

    /**
     * 出现异常
     * <p>
     * 当 websocket 建立连接时出现异常会触发这个注解修饰的方法
     *
     * @param session 会话
     */
    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("连接出现异常");
        t.printStackTrace();
    }
}