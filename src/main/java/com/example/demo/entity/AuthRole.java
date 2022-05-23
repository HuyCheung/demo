package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.entity.enums.AuthRoleStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限-角色
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_role")
@ApiModel(value = "AuthRole对象", description = "权限-角色")
public class AuthRole extends Model<AuthRole> {

    @ApiModelProperty("角色 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("角色代码")
    @TableField("code")
    private String code;

    @ApiModelProperty("状态  1:正常 normal 9:禁用 disable")
    @TableField("status")
    private AuthRoleStatus status;

    @ApiModelProperty("角色描述")
    @TableField("description")
    private String description;

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