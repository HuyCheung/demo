package com.example.demo.mapper;

import com.example.demo.entity.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限-用户 Mapper 接口
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

}
