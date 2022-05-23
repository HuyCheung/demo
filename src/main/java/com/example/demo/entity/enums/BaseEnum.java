package com.example.demo.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

/**
 * 基本枚举
 *
 * @author Huy Cheung
 * @date 2022/05/21
 */
public interface BaseEnum<T extends Serializable> extends IEnum<T> {

    /**
     * 描述
     *
     * @return {@link String}
     */
    String getDescription();

}