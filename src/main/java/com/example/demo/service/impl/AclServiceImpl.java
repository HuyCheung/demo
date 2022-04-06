package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Acl;
import com.example.demo.mapper.AclMapper;
import com.example.demo.service.AclService;
import org.springframework.stereotype.Service;

@Service
public class AclServiceImpl extends ServiceImpl<AclMapper, Acl> implements AclService {

}