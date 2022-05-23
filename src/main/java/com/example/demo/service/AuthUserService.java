package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.AuthUser;

/**
 * <p>
 * 权限-用户 服务类
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
public interface AuthUserService extends IService<AuthUser> {
    /**
     * 登录
     * <p>
     * 根据用户名/电话/邮箱匹配数据库里是否存在，参数和salt计算匹配
     *
     * @param account  账户
     * @param password 密码
     */
    void doLogin(String account, String password);
}