package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.entity.enums.AuthResourceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限-资源
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_resource")
@ApiModel(value = "AuthResource对象", description = "权限-资源")
public class AuthResource extends Model<AuthResource> {

    @ApiModelProperty("资源 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("资源代码")
    @TableField("code")
    private String code;

    @ApiModelProperty("资源名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("资源类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("状态 1:正常 normal 9：过滤 filtering（排除保护-都可以访问这个api）")
    @TableField("status")
    private AuthResourceStatus status;

    @ApiModelProperty("资源描述")
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