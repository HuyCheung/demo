package com.example.demo.cache;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.AuthCustomizeMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AuthUserCache {

    @Resource
    private AuthCustomizeMapper authCustomizeMapper;

    /**
     * 异步刷新线程池
     */
    ListeningExecutorService backgroundRefreshPools =
            MoreExecutors.listeningDecorator(new ThreadPoolExecutor(10, 10,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>()));

    /**
     * guava cache 缓存实体
     */
    LoadingCache<Long, AuthUserDTO> cache = CacheBuilder.newBuilder()
            // 缓存刷新时间
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            // 设置缓存个数
            .maximumSize(500)
            .build(new CacheLoader<>() {
                @NotNull
                @Override
                // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                public AuthUserDTO load(@NotNull Long appKey) {
                    return getUserFromDB(appKey);
                }

                @NotNull
                @Override
                // 刷新时，开启一个新线程异步刷新，老请求直接返回旧值，防止耗时过长
                public ListenableFuture<AuthUserDTO> reload(@NotNull Long key, @NotNull AuthUserDTO oldValue) throws Exception {
                    return backgroundRefreshPools.submit(() -> getUserFromDB(key));
                }

                /**
                 * 从数据库查询用户
                 *
                 * @param id id
                 * @return {@link AuthUserDTO}
                 */// 数据库进行查询
                private AuthUserDTO getUserFromDB(Long id) {
                    log.info("load user info from db!account:{}", id);

                    // 获取用户权限消息
                    AuthUserDTO authUser = authCustomizeMapper.authUserInfo(id);
                    if (Objects.isNull(authUser)) {
                        throw new CustomizeException("用户不存在");
                    }
                    return authUser;
                }
            });

    /**
     * 对外暴露的方法
     * 从缓存中取entry，没取到就走数据库
     */
    public AuthUserDTO getUser(Long userId) throws ExecutionException {
        return cache.get(userId);
    }

    /**
     * 销毁时关闭线程池
     */
    @PreDestroy
    public void destroy() {
        try {
            backgroundRefreshPools.shutdown();
        } catch (Exception e) {
            log.error("thread pool showdown error!e:{}", e.getMessage());
        }

    }
}