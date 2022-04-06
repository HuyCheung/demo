package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserRole;
import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    int updateBatchSelective(List<UserRole> list);

    int insertOrUpdate(UserRole record);

    int insertOrUpdateSelective(UserRole record);
}