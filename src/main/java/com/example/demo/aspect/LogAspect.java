package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * ..表示包及子包 该方法代表controller层的所有方法
     * TODO 路径需要根据自己项目定义
     */
    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void controllerMethod() {
    }


    /**
     * 方法执行前
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes, "attributes must not be null");
        HttpServletRequest request = attributes.getRequest();
        // todo 执行日志记录
        // log.info("{} url: {}, param: {}", request.getMethod(), request.getRequestURI(), ArrayUtil.toString(joinPoint.getArgs()));

    }
}