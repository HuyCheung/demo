package com.example.demo.socket;

import com.example.demo.entity.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ws会话管理器
 *
 * @author Huy Cheung
 * @date 2022/04/07
 */
@Slf4j
public class WsSessionManager {
    /**
     * 保存连接 session 的地方
     */
    public static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();

    /**
     * socket信息
     */
    public static ConcurrentHashMap<String, SocketInfo> socketInfoPool = new ConcurrentHashMap<>();


    /**
     * 添加 session
     *
     * @param session   会话
     * @param sessionId 会话id
     */
    public static void add(String sessionId, Session session) {
        // 添加 session
        sessionPool.put(sessionId, session);
    }

    /**
     * 删除 session,会返回删除的 session
     *
     * @param sessionId 会话id
     * @return {@link Session}
     */
    public static Session remove(String sessionId) {
        // 删除 session
        return sessionPool.remove(sessionId);
    }

    /**
     * 删除并同步关闭连接
     *
     * @param sessionId 会话id
     */
    public static void removeAndClose(String sessionId) {
        Session session = remove(sessionId);
        if (session != null) {
            try {
                // 关闭连接
                session.close();
            } catch (IOException e) {
                // todo: 关闭出现异常处理
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得 session
     *
     * @param sessionId 会话id
     * @return {@link Session}
     */
    public static Session get(String sessionId) {
        // 获得 session
        return sessionPool.get(sessionId);
    }

    /**
     * 初始化连接
     *
     * @param sessionId 会话id
     * @param session   会话
     * @param userInfo  用户信息
     */
    public static void initConnect(String sessionId, Session session, UserDTO userInfo) {
        socketInfoPool.put(sessionId, new SocketInfo(sessionId, System.currentTimeMillis(), userInfo));
        initSocketInfo(sessionId, userInfo);
    }

    /**
     * 断开连接
     *
     * @param sessionId 会话id
     */
    public static void disconnected(String sessionId) {
        removeAndClose(sessionId);
        socketInfoPool.remove(sessionId);
    }

    /**
     * 批量断开连接
     *
     * @param sessionIds 会话id
     */
    public static void batchDisconnected(List<String> sessionIds) {
        for (String sessionId : sessionIds) {
            removeAndClose(sessionId);
            socketInfoPool.remove(sessionId);
        }
    }

    /**
     * 初始化连接信息
     *
     * @param sessionId 会话id
     * @param userInfo  用户信息
     */
    public static void initSocketInfo(String sessionId, UserDTO userInfo) {
        socketInfoPool.put(sessionId, new SocketInfo(sessionId, System.currentTimeMillis(), userInfo));
    }

    /**
     * 获取用户信息
     *
     * @param sessionId 会话id
     * @return {@link UserDTO}
     */
    public static UserDTO getUserInfo(String sessionId) {
        return socketInfoPool.get(sessionId).getUserInfo();
    }

    /**
     * 更新最后通信时间
     *
     * @param sessionId 会话id
     */
    public static void updateLastComTime(String sessionId) {
        socketInfoPool.get(sessionId).setLastComTime(System.currentTimeMillis());
    }

    /**
     * 指定会话发送消息
     *
     * @param sessionId 会话id
     * @param message   消息
     */
    public static void sendMessage(String sessionId, String message) throws IOException {
        sessionPool.get(sessionId).getBasicRemote().sendText(message);
    }


    /**
     * Socket信息
     *
     * @author Huy Cheung
     * @date 2022/04/07
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SocketInfo {

        /**
         * 会话id
         */
        private String sessionId;

        /**
         * 最后通信时间
         */
        private Long lastComTime;

        /**
         * 用户信息
         */
        private UserDTO userInfo;

    }
}