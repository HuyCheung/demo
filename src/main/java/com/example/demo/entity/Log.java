package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 操作日志表
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`sys_log`")
public class Log {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作类型
     */
    @TableField(value = "`operate_type`")
    private String operateType;

    /**
     * 操作者账号
     */
    @TableField(value = "`operate_account`")
    private String operateAccount;

    /**
     * 操作者名称
     */
    @TableField(value = "`operate_username`")
    private String operateUsername;

    /**
     * 操作类型
     */
    @TableField(value = "`operate_content`")
    private String operateContent;

    /**
     * 操作ip
     */
    @TableField(value = "`operate_ip`")
    private Integer operateIp;

    /**
     * 创建时间
     */
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}