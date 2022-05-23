package com.example.demo.entity.enums;

/**
 * 身份验证用户状态
 * 用户状态
 *
 * @author Huy Cheung
 * @date 2022/05/21
 */
public enum AuthUserCreateWhere implements BaseEnum<Integer> {
    WEB(1, "web"),
    android(2, "android"),
    IOS(3, "ios"),
    WINDOWS(4, "windows"),
    MAC(5, "mac"),
    LINUX(6, "linux");

    private final int value;
    private final String desc;

    AuthUserCreateWhere(int value, String desc) {
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