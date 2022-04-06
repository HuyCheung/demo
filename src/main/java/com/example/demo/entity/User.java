package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 用户表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`sys_user`")
public class User {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @TableField(value = "`account`")
    private String account;

    /**
     * 用户名
     */
    @TableField(value = "`username`")
    private String username;

    /**
     * 真实姓名
     */
    @TableField(value = "`real_name`")
    private String realName;

    /**
     * 邮箱
     */
    @TableField(value = "`email`")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "`phone`")
    private String phone;

    /**
     * 出生日期
     */
    @TableField(value = "`birthday`")
    private LocalDateTime birthday;

    /**
     * 密码，经过加密
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 状态 0:删除，1:正常，2:冻结
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 备注
     */
    @TableField(value = "`remark`")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}