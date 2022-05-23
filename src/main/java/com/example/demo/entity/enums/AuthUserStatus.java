package com.example.demo.entity.enums;

/**
 * 身份验证用户状态
 * 用户状态
 *
 * @author Huy Cheung
 * @date 2022/05/21
 */
public enum AuthUserStatus implements BaseEnum<Integer> {
    NORMAL(1, "正常"),
    LOCKED(2, "锁定"),
    DELETED(3, "删除"),
    ILLEGAL(4, "非法");

    private final int value;
    private final String desc;

    AuthUserStatus(int value, String desc) {
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