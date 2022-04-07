package com.example.demo.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.result.ResultResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 管理员用户表 前端控制器
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-03-12
 */
@Validated
@RestController
@RequestMapping("user")
public class LoginController {

    @Value("${jasypt.encryptor.password}")
    private String salt;

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param account  账户 account/username/email/phone
     * @param password 密码
     * @return {@link String}
     */
    @GetMapping("doLogin")
    public ResultResponse<Void> doLogin(@NotBlank(message = "账户不能为空") String account, @NotBlank(message = "密码不能为空") String password) {
        String ciphertext = SaSecureUtil.md5BySalt(password, salt);
        return userService.doLogin(account,ciphertext);
    }

    @GetMapping("logout")
    public ResultResponse<Void> logout() {
        StpUtil.logout();
        return ResultResponse.success();
    }
}