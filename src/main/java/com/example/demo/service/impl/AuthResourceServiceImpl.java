package com.example.demo.service.impl;

import com.example.demo.entity.AuthResource;
import com.example.demo.mapper.AuthResourceMapper;
import com.example.demo.service.AuthResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限-资源 服务实现类
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-05-21
 */
@Service
public class AuthResourceServiceImpl extends ServiceImpl<AuthResourceMapper, AuthResource> implements AuthResourceService {

}
