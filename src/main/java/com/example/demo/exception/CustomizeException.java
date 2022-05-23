package com.example.demo.exception;

import cn.dev33.satoken.util.SaResult;
import com.example.demo.result.ReturnCode;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author Huy Cheung
 * @date 2022/05/21
 */
@Getter
public class CustomizeException extends RuntimeException {
    private final Integer code;

    public CustomizeException(String message) {
        super(message);
        this.code = SaResult.CODE_ERROR;
    }

    public CustomizeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomizeException(ReturnCode.Default returnCode) {
        super(returnCode.getMessage());
        this.code = returnCode.getCode();
    }
}