package com.example.demo.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.AuthUser;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.AuthUserMapper;
import com.example.demo.result.ReturnCode;
import com.example.demo.service.AuthUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 权限-用户 服务实现类
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

    @Override
    public void doLogin(String account, String password) {
        AuthUser authUser = lambdaQuery()
                .eq(AuthUser::getUsername, account).or()
                .eq(AuthUser::getPhone, account).or()
                .eq(AuthUser::getEmail, account).one();
        if (Objects.isNull(authUser)) {
            throw new CustomizeException(ReturnCode.Default.USER_NOT_EXIST);
        }
        switch (authUser.getStatus()) {
            case LOCKED -> throw new CustomizeException(ReturnCode.Default.USER_LOCKED);
            case DELETED -> throw new CustomizeException(ReturnCode.Default.USER_DELETED);
            case ILLEGAL -> throw new CustomizeException(ReturnCode.Default.USER_ILLEGAL);
        }
        String ciphertext = SaSecureUtil.md5(password + authUser.getSalt());
        if (!ciphertext.equalsIgnoreCase(authUser.getPassword())) {
            throw new CustomizeException(ReturnCode.Default.USER_PASSWORD_INCORRECT);
        }
        StpUtil.login(authUser.getId());
    }
}