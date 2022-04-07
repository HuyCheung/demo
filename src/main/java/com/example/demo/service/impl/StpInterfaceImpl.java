package com.example.demo.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.example.demo.cache.UserCache;
import com.example.demo.entity.dto.UserDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

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
    private UserCache userCache;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @SneakyThrows
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserDTO user = userCache.getUser((String) loginId);
        return user.getAclList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    @SneakyThrows
    public List<String> getRoleList(Object loginId, String loginType) {
        UserDTO user = userCache.getUser((String) loginId);
        return user.getRoles();
    }

}