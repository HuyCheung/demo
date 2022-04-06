package com.example.demo.cache;

import com.example.demo.constant.Status;
import com.example.demo.entity.RoleAcl;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAcl;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.service.RoleAclService;
import com.example.demo.service.UserAclService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserCache {

    @Resource
    private UserService userService;
    @Resource
    private UserAclService userAclService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleAclService roleAclService;

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
    LoadingCache<String, UserDTO> cache = CacheBuilder.newBuilder()
            // 缓存刷新时间
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            // 设置缓存个数
            .maximumSize(500)
            .build(new CacheLoader<>() {
                @Override
                // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                public UserDTO load(String appKey) {
                    return getUserFromDB(appKey);
                }

                @Override
                // 刷新时，开启一个新线程异步刷新，老请求直接返回旧值，防止耗时过长
                public ListenableFuture<UserDTO> reload(String key, UserDTO oldValue) throws Exception {
                    return backgroundRefreshPools.submit(() -> getUserFromDB(key));
                }

                // 数据库进行查询
                private UserDTO getUserFromDB(String account) {
                    log.info("load user info from db!account:{}", account);
                    // 获取用户基础信息
                    User user = userService.lambdaQuery()
                            .eq(User::getStatus, Status.NORMAL)
                            .eq(User::getAccount, account)
                            .one();
                    if (Objects.isNull(user)) {
                        throw new CustomException("用户不存在");
                    }
                    user.setPassword(null);
                    UserDTO userDTO = UserDTO.convertDTO(user);
                    // 获取用户角色和权限
                    List<UserRole> roles = userRoleService.lambdaQuery().eq(UserRole::getAccount, account).list();
                    List<String> aclList = Lists.newArrayList();
                    if (!CollectionUtils.isEmpty(roles)) {
                        userDTO.setRoles(roles.parallelStream().map(UserRole::getRoleCode).toList());
                        // 角色拥有的权限
                        List<RoleAcl> roleAclList = roleAclService.lambdaQuery().in(RoleAcl::getRoleCode, roles).list();
                        if (!CollectionUtils.isEmpty(roleAclList)) {
                            aclList.addAll(roleAclList.parallelStream().map(RoleAcl::getAclCode).toList());
                        }
                    }

                    List<UserAcl> userAclList = userAclService.lambdaQuery().eq(UserAcl::getAccount, account).list();
                    if (!CollectionUtils.isEmpty(userAclList)) {
                        aclList.addAll(userAclList.parallelStream().map(UserAcl::getAclCode).toList());
                    }
                    userDTO.setAclList(aclList);
                    return userDTO;
                }
            });

    /**
     * 对外暴露的方法
     * 从缓存中取entry，没取到就走数据库
     */
    public UserDTO getUser(String name) throws ExecutionException {
        return cache.get(name);
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