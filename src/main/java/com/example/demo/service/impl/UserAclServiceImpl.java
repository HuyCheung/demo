package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UserAcl;
import com.example.demo.mapper.UserAclMapper;
import com.example.demo.service.UserAclService;
import org.springframework.stereotype.Service;

@Service
public class UserAclServiceImpl extends ServiceImpl<UserAclMapper, UserAcl> implements UserAclService {
}