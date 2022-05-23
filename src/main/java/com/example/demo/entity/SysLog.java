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
 * 操作日志表
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value = "SysLog对象", description = "操作日志表")
public class SysLog extends Model<SysLog> {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("操作类型")
    @TableField("operate_type")
    private String operateType;

    @ApiModelProperty("操作者账号")
    @TableField("operate_account")
    private String operateAccount;

    @ApiModelProperty("操作者名称")
    @TableField("operate_username")
    private String operateUsername;

    @ApiModelProperty("操作类型")
    @TableField("operate_content")
    private String operateContent;

    @ApiModelProperty("操作ip")
    @TableField("operate_ip")
    private Integer operateIp;

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
