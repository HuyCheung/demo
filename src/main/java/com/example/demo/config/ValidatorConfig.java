package com.example.demo.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 验证器配置
 * 当一个校验失败，其它就不必校验了
 *
 * @author Huy Cheung
 * @date 2022/03/12
 */
@Configuration
public class ValidatorConfig {

    /**
     * 验证器
     * <p>
     * 当校验到第一个错误就停止继续校验
     *
     * @return {@link Validator}
     */
    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    }

}