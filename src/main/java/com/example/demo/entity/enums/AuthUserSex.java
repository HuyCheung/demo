package com.example.demo.entity.enums;

/**
 * 身份验证用户状态
 * 用户状态
 *
 * @author Huy Cheung
 * @date 2022/05/21
 */
public enum AuthUserSex implements BaseEnum<Integer> {
    MAN(1, "男"),
    WOMAN(2, "女");

    private final int value;
    private final String desc;

    AuthUserSex(int value, String desc) {
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