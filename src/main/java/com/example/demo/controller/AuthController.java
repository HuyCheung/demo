package com.example.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.example.demo.cache.AuthUserCache;
import com.example.demo.dto.AuthUserDTO;
import com.example.demo.result.ResultResponse;
import com.example.demo.result.ReturnCode;
import com.example.demo.service.AuthUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("auth")
public class AuthController {

    private final static String CAPTCHA = "captcha";
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
    public ResultResponse<Void> doLogin(@NotBlank(message = "账户不能为空") String account, @NotBlank(message = "密码不能为空") String password, @NotBlank(message = "验证码不能为空") String captcha) {
        String saveCaptcha = (String) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(CAPTCHA, RequestAttributes.SCOPE_SESSION);
        RequestContextHolder.getRequestAttributes().setAttribute(CAPTCHA, "", RequestAttributes.SCOPE_SESSION);
        if (!captcha.equalsIgnoreCase(saveCaptcha)) {
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
    public void captcha(HttpSession session, HttpServletResponse response) throws Exception {
        ICaptcha captcha = CaptchaUtil.createGifCaptcha(200, 100);
        session.setAttribute(CAPTCHA, captcha.getCode());
        captcha.write(response.getOutputStream());
    }

}