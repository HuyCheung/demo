package com.example.demo.entity.enums;

/**
 * 用户状态
 *
 * @author Huy Cheung
 * @date 2022/05/21
 */
public enum AuthResourceStatus implements BaseEnum<Integer> {
    NORMAL(1, "正常"),
    FILTERING(9, "过滤");

    private final int value;
    private final String desc;

    AuthResourceStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}