package com.example.demo.service.impl;

import com.example.demo.entity.AuthRole;
import com.example.demo.mapper.AuthRoleMapper;
import com.example.demo.service.AuthRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限-角色 服务实现类
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements AuthRoleService {

}
