package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role;
import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    int updateBatchSelective(List<Role> list);

    int insertOrUpdate(Role record);

    int insertOrUpdateSelective(Role record);
}