package com.example.demo.handle;

import cn.dev33.satoken.util.SaResult;
import com.example.demo.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回对象配置类
 */
@Slf4j
@ControllerAdvice
public class GlobalResultHandler implements ResponseBodyAdvice<Object> {

    private final static String PACKAGE_PATH = "com.example.demo.controller";

    /**
     * 针对以下情况 不做 统一包装处理
     * 1.返回值为 void 的方法
     * 2.返回值为 String 类型的方法
     * 3.返回值为 Result 类型的方法
     * 4.在包路径 PACKAGE_PATH 以外的方法
     *
     * @param returnType    返回类型
     * @param converterType 转换器类型
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getMethod().getReturnType().isAssignableFrom(Void.TYPE)
                && !returnType.getMethod().getReturnType().isAssignableFrom(String.class)
                && !returnType.getMethod().getReturnType().isAssignableFrom(SaResult.class)
                && returnType.getDeclaringClass().getPackage().getName().contains(PACKAGE_PATH);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultResponse) {
            return body;
        }
        return ResultResponse.success(body);
    }
}