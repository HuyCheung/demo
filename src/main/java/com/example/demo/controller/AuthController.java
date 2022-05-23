package com.example.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.cache.AuthUserCache;
import com.example.demo.dto.AuthUserDTO;
import com.example.demo.result.ResultResponse;
import com.example.demo.result.ReturnCode;
import com.example.demo.service.AuthUserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("auth")
public class AuthController {
    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserCache authUserCache;

    /**
     * 登录
     *
     * @param account  账户 username/email/phone
     * @param password 密码
     */
    @GetMapping("doLogin")
    public ResultResponse<Void> doLogin(@NotBlank(message = "账户不能为空") String account, @NotBlank(message = "密码不能为空") String password, @NotBlank(message = "验证码不能为空") String captcha, HttpServletRequest request) {
        if (!CaptchaUtil.ver(captcha, request)) {
            return ResultResponse.fail(ReturnCode.Default.USER_VERIFICATION_FAILED);
        }
        authUserService.doLogin(account, password);
        return ResultResponse.success("登录成功");
    }

    /**
     * 用户信息
     */
    @GetMapping("userInfo")
    public AuthUserDTO userInfo() throws ExecutionException {
        long userId = StpUtil.getLoginIdAsLong();
        return authUserCache.getUser(userId);
    }

    /**
     * 注销
     */
    @GetMapping("logout")
    public ResultResponse<Void> logout() {
        StpUtil.logout();
        return ResultResponse.success("注销登录成功");
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

}