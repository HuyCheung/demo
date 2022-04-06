package com.example.demo.exception;

import cn.dev33.satoken.util.SaResult;
import com.example.demo.result.ReturnCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(String message) {
        super(message);
        this.code = SaResult.CODE_ERROR;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ReturnCode.Default returnCode) {
        super(returnCode.getMessage());
        this.code = returnCode.getCode();
    }
}