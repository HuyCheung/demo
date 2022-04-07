package com.example.demo.handler;

import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.exception.CustomException;
import com.example.demo.result.ResultResponse;
import com.example.demo.result.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultResponse<Void> error(Exception e) {
        e.printStackTrace();
        // 通用异常结果
        return ResultResponse.fail(e.getMessage());
    }

    /*
      -------- 指定异常处理方法 --------
     */

    /**
     * 空指针
     *
     * @param e e
     * @return {@link SaResult}
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultResponse<Void> error(NullPointerException e) {
        e.printStackTrace();
        return ResultResponse.fail(e.getMessage());
    }

    /**
     * 登录异常
     *
     * @param e e
     * @return {@link SaResult}
     */
    @ExceptionHandler(SaTokenException.class)
    @ResponseBody
    public ResultResponse<Void> error(SaTokenException e) {
        if (e instanceof NotLoginException ee) {
            // 如果是未登录异常
            return ResultResponse.fail(ee.getMessage());
        } else if (e instanceof NotRoleException ee) {
            // 如果是角色异常
            return ResultResponse.fail("无此角色：" + ee.getRole());
        } else if (e instanceof NotPermissionException ee) {
            // 如果是权限异常
            return ResultResponse.fail("无此权限：" + ee.getCode());
        } else if (e instanceof DisableLoginException ee) {
            // 如果是被封禁异常
            return ResultResponse.fail("账号被封禁：" + ee.getDisableTime() + "秒后解封");
        } else {
            return ResultResponse.fail("登录异常：" + e.getMessage());
        }
    }

    /**
     * http调用
     *
     * @param e e
     * @return {@link SaResult}
     */
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResultResponse<Void> error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        return ResultResponse.fail(ReturnCode.Default.SERVICE_CALL_EXCEPTION);
    }

    /**
     * -------- 参数校验异常处理方法 --------
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResultResponse<Void> error(ConstraintViolationException e) {
        e.printStackTrace();
        return ResultResponse.fail(ReturnCode.Default.PARAM_VALIDATED, e.getMessage());
    }

    /**
     * -------- 自定义定异常处理方法 --------
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResultResponse<Void> error(CustomException e) {
        e.printStackTrace();
        return ResultResponse.fail(e.getMessage());
    }
}