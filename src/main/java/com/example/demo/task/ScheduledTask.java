package com.example.demo.task;

import com.example.demo.socket.WsSessionManager;
import com.google.common.collect.Lists;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {
    /**
     * 清理过期ws会话
     */
    @Scheduled(fixedDelay = 600000)
    public void cleanWsSession() {
        List<String> disconnectedSessionIds = Lists.newArrayList();
        long now = System.currentTimeMillis();
        WsSessionManager.socketInfoPool.forEach((sessionId, socketInfo) -> {
            if (now - socketInfo.getLastComTime() > TimeUnit.MINUTES.toMillis(10)) {
                disconnectedSessionIds.add(sessionId);
            }
        });
        WsSessionManager.batchDisconnected(disconnectedSessionIds);
    }
}