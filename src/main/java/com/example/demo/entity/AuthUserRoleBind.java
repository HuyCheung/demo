package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限-用户角色关联
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_user_role_bind")
@ApiModel(value = "AuthUserRoleBind对象", description = "权限-用户角色关联")
public class AuthUserRoleBind extends Model<AuthUserRoleBind> {

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户 ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("角色 ID")
    @TableField("role_id")
    private Long roleId;

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
