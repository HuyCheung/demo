package com.example.demo.mapper;

import com.example.demo.entity.AuthUserRoleBind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限-用户角色关联 Mapper 接口
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Mapper
public interface AuthUserRoleBindMapper extends BaseMapper<AuthUserRoleBind> {

}
