package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.RoleAcl;
import java.util.List;

public interface RoleAclMapper extends BaseMapper<RoleAcl> {
    int updateBatchSelective(List<RoleAcl> list);

    int insertOrUpdate(RoleAcl record);

    int insertOrUpdateSelective(RoleAcl record);
}