package com.example.demo.entity.dto;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理员用户表
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 密码，经过加密
     */
    private String password;

    /**
     * 状态 0:删除，1:正常，2:冻结
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属角色集合
     */
    private List<String> roles;

    /**
     * 所属权限集合
     */
    private List<String> aclList;

    public static UserDTO convertDTO(User user) {
        return BeanUtil.copyProperties(user, UserDTO.class);
    }


}