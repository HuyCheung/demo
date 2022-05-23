package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.entity.enums.AuthUserCreateWhere;
import com.example.demo.entity.enums.AuthUserSex;
import com.example.demo.entity.enums.AuthUserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限-用户
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_user")
@ApiModel(value = "AuthUser对象", description = "权限-用户")
public class AuthUser extends Model<AuthUser> {

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名（昵称）")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码 ，计算公式：password=MD5(passwd+salt)")
    @TableField("password")
    private String password;

    @ApiModelProperty("Salt值")
    @TableField("salt")
    private String salt;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("电话号码（唯一）")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("电子邮件（唯一）")
    @TableField("email")
    private String email;

    @ApiModelProperty("性别(1.男 man 2.女 woman )")
    @TableField("sex")
    private AuthUserSex sex;

    @ApiModelProperty("账户状态（1.正常 normal 2.锁定 locked 3.删除 deleted  4.非法 illegal）")
    @TableField("status")
    private AuthUserStatus status;

    @ApiModelProperty("创建来源(1.web 2.android 3.ios 4.win 5.mac 6.linux)")
    @TableField("create_where")
    private AuthUserCreateWhere createWhere;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}