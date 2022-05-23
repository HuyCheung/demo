package com.example.demo.mapper;

import com.example.demo.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
