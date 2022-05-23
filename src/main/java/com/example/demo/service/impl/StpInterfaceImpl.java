package com.example.demo.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.example.demo.cache.AuthUserCache;
import com.example.demo.dto.AuthResourceDTO;
import com.example.demo.dto.AuthRoleDTO;
import com.example.demo.dto.AuthUserDTO;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 * <p>
 * 完成Sa-Token的自定义权限验证扩展
 *
 * @author Huy Cheung
 * @date 2022/03/15
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private AuthUserCache authUserCache;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @SneakyThrows
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        AuthUserDTO user = authUserCache.getUser((Long) loginId);
        List<AuthResourceDTO> resources = user.getResources();
        if (CollectionUtils.isEmpty(resources)) {
            return Lists.newArrayList();
        }
        return resources.stream().map(AuthResourceDTO::getCode).toList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    @SneakyThrows
    public List<String> getRoleList(Object loginId, String loginType) {
        AuthUserDTO user = authUserCache.getUser((Long) loginId);
        List<AuthRoleDTO> roles = user.getRoles();
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }
        return roles.stream().map(AuthRoleDTO::getCode).toList();
    }

}